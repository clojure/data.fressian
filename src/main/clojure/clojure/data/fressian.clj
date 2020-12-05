;; Copyright (c) Cognitect, Inc. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns ^{:author "Stuart Halloway"
      :doc "Read/write fressian data. See http://www.edn-format.org/"}
  clojure.data.fressian
  (:refer-clojure :exclude (read))
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str])
  (:import
   [clojure.lang IRecord Ratio]
   [java.io InputStream OutputStream]
   [java.nio ByteBuffer]
   [org.fressian FressianWriter StreamingWriter FressianReader TaggedObject Writer Reader]
   [org.fressian.handlers WriteHandler ReadHandler ILookup WriteHandlerLookup]
   [org.fressian.impl ByteBufferInputStream BytesOutputStream InheritanceLookup]))

(set! *warn-on-reflection* true)

(defn- write-named [tag ^Writer w s]
  (.writeTag w tag 2)
  (.writeObject w (namespace s) true)
  (.writeObject w (name s) true))


(defn- ^ByteBuffer bytestream->buf
  "Return a readable buf over the current internal state of a
   BytesOutputStream."
  [^BytesOutputStream stream]
  (ByteBuffer/wrap (.internalBuffer stream) 0 (.length stream)))

(defprotocol FressianReadable
  (to-input-stream [obj] "Implementation detail."))

(extend-protocol FressianReadable
  Object
  (to-input-stream
   [obj]
   (io/input-stream obj))
  
  ByteBuffer
  (to-input-stream
   [bb]
   (io/input-stream (ByteBufferInputStream. bb))))

(defn associative-lookup
  "Build an ILookup from an associative collection."
  [o]
  (reify ILookup
         (valAt [_ k] (get o k))))

(defn inheritance-lookup
  "Returns an inheritance aware lookup based on lookup that will match
   subclasses as well as exact matches.  Will walk inheritance hierarchy
   once per new type encountered to find the best match, then cache
   results."
  [lookup]
  (InheritanceLookup. lookup))

(defn- class-sym
  "Returns the class name of inst as a symbol."
  [^Object inst]
  (-> inst (.getClass) (.getName) symbol))

(def clojure-write-handlers
  "Standard set of write handlers for Clojure data."
  {Character
   {"char"
    (reify WriteHandler
           (write [_ w ch]
                  (.writeTag w "char" 1)
                  (.writeInt w (int ch))))}
   Ratio
   {"ratio"
    (reify WriteHandler
           (write [_ w n]
                  (.writeTag w "ratio" 2)
                  (.writeObject w (.numerator ^Ratio n))
                  (.writeObject w (.denominator ^Ratio n))))}

   IRecord
   {"clojure/record"
    (reify WriteHandler
           (write [_ w rec]
                  (.writeTag w "record" 2)
                  (.writeObject w (class-sym rec) true)
                  (.writeTag w "map" 1)
                  (.beginClosedList ^StreamingWriter w)
                  (reduce-kv
                   (fn [^Writer w k v]
                     (.writeObject w k true)
                     (.writeObject w v))
                   w
                   rec)
                  (.endList ^StreamingWriter w)))}
   
   clojure.lang.Keyword
   {"key"
    (reify WriteHandler
           (write [_ w s]
                  (write-named "key" w s)))}
   
   clojure.lang.BigInt
   {"bigint"
    (reify WriteHandler
           (write [this w d]
                  (let [^BigInteger bi (if (instance? clojure.lang.BigInt d)
                                         (.toBigInteger ^clojure.lang.BigInt d)
                                         d)]
                    (.writeTag w "bigint" 1)
                    (.writeBytes w (.toByteArray bi)))))}

   clojure.lang.Symbol
   {"sym"
    (reify WriteHandler
           (write [_ w s]
                  (write-named "sym" w s)))}})

(defn field-caching-writer
  "Returns a record writer that caches values for keys
   matching cache-pred, which is typically specified
   as a set, e.g. (field-caching-writer #{:color})"
  [cache-pred]
  (reify WriteHandler
         (write [_ w rec]
                (.writeTag w "record" 2)
                (.writeObject w (class-sym rec) true)
                (.writeTag w "map" 1)
                (.beginClosedList ^StreamingWriter w)
                (reduce-kv
                 (fn [^Writer w k v]
                   (.writeObject w k true)
                   (.writeObject w v (boolean (cache-pred k))))
                 w
                 rec)
                (.endList ^StreamingWriter w))))

(defn- record-map-constructor-name
  "Return the map constructor for a record"
  [rname]
  (let [comps (str/split (str rname) #"\.")]
    (symbol (->> (butlast comps) (map #(str/replace % "_" "-"))
                 (str/join "."))
            (str "map->" (last comps)))))

(def clojure-read-handlers
  "Standard set of read handlers for Clojure data."
  {"bigint"
   (reify ReadHandler
          (read [_ rdr tag component-count]
                (let [^bytes bibytes (.readObject rdr)]
                  (bigint (BigInteger. bibytes)))))
   
   "byte"
   (reify ReadHandler (read [_ rdr tag component-count]
                            (byte (.readObject rdr))))

   "record"
   (reify ReadHandler (read [_ rdr tag component-count]
                            (let [rname (.readObject rdr)
                                  rmap (.readObject rdr)]
                              (if-let [rcons (resolve (record-map-constructor-name rname))]
                                (rcons rmap)
                                (TaggedObject. "record" (into-array Object [rname rmap]))))))
   
   "char"
   (reify ReadHandler (read [_ rdr tag component-count]
                            (char (.readObject rdr))))
   
   "ratio"
   (reify ReadHandler (read [_ rdr tag component-count]
                            (Ratio. (biginteger (.readObject rdr))
                                    (biginteger (.readObject rdr)))))
   "key"
   (reify ReadHandler (read [_ rdr tag component-count]
                            (keyword (.readObject rdr) (.readObject rdr))))
   "sym"
   (reify ReadHandler (read [_ rdr tag component-count]
                            (symbol (.readObject rdr) (.readObject rdr))))
   "map"
   (reify ReadHandler (read [_ rdr tag component-count]
                            (let [kvs ^java.util.List (.readObject rdr)]
                              (if (< (.size kvs) 16)
                                (clojure.lang.PersistentArrayMap. (.toArray kvs))
                                (clojure.lang.PersistentHashMap/create (seq kvs))))))})

(defn ^Writer create-writer
  "Create a fressian writer targeting out. Handlers must be
   a nested map of type => tag => WriteHandler wrapped with
   associative-lookup and inheritance-lookup. See
   clojure-write-handlers for an example."
  [^OutputStream out & {:keys [handlers]}]
  (FressianWriter. out (or handlers (-> clojure-write-handlers associative-lookup inheritance-lookup))))

(defn ^Reader create-reader
  "Create a fressian reader targeting in, which must be compatible
   with clojure.java.io/input-stream.  Handlers must be a map of
   tag => ReadHandler wrapped in associative-lookup. See
   clojure-read-handlers for an example."
  [^InputStream in & {:keys [handlers checksum?]}]
  (FressianReader. in
                   (or handlers (associative-lookup clojure-read-handlers))
                   (boolean checksum?)))

(defn read-object
  "Read a single object from a fressian reader."
  [^Reader rdr]
  (.readObject rdr))

(defn write-object
  "Write a single object to a fressian writer. Returns the writer."
  [^Writer writer obj]
  (.writeObject writer obj))

(defn read
  "Convenience method for reading a single fressian object.
   Takes same options as create-reader.  Readable can be
   any type supported by clojure.java.io/input-stream, or
   a ByteBuffer."
  [readable & options]
  (.readObject ^Reader (apply create-reader (to-input-stream readable) options)))

(defn ^ByteBuffer write
  "Convenience method for writing a single object.  Returns a
   byte buffer.  Options are the same as for create-writer,
   with one additional option.  If footer? is specified, will
   write a fressian footer after writing the object."
  ([obj & options]
     (let [{:keys [footer?]} (when options (apply hash-map options))
           bos (BytesOutputStream.)
           writer ^Writer (apply create-writer bos options)]
       (.writeObject writer obj)
       (when footer?
         (.writeFooter writer))
       (bytestream->buf bos))))

(defn tagged-object?
  "Returns true if o is a tagged object, which will occur when
   the reader does not recognized a specific type.  Use tag
   and tagged-value to access the contents of a tagged-object."
  [o]
  (instance? TaggedObject o))

(defn tag
  "Returns the tag if object is a tagged-object, else nil."
  [^TaggedObject obj]
  (when (tagged-object? obj)
    (.getTag obj)))

(defn tagged-value
  "Returns the value (an Object arrray) wrapped by obj, or nil
   if obj is not a tagged object."
  [^TaggedObject obj]
  (when (tagged-object? obj)
    (.getValue obj)))

(defn ^Writer write-footer
  [^Writer writer]
  (.writeFooter writer))

(defn ^Writer begin-closed-list
  "Begin writing a fressianed list.  To end the list, call end-list.
   Used to write sequential data whose size is not known in advance."
  [^StreamingWriter writer]
  (.beginClosedList writer))

(defn ^Writer end-list
  "Ends a list begun with begin-closed-list."
  [^StreamingWriter writer]
  (.endList writer))

(defn ^Writer begin-open-list
  "Advanced.  Writes fressian code to begin an open list.  An
   open list can be terminated either by a call to end-list,
   or by simply closing the stream.  Used to write sequential
   data whose size is not known in advance, in contexts where
   stream failure can safely be interpreted as end of list."
  [^StreamingWriter writer]
  (.beginOpenList writer))


