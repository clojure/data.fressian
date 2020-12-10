;; Copyright (c) Cognitect, Inc. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file epl-v10.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.  You must not
;; remove this notice, or any other, from this software.

(ns clojure.data.fressian-test
  (:refer-clojure :exclude (read))
  (:import
   clojure.lang.Ratio
   [java.nio ByteBuffer]
   [org.fressian.impl BytesOutputStream]
   [org.fressian Reader Writer])
  (:require
   [clojure.data.fressian :as fress]
   [clojure.data.generators :as gen]
   [clojure.test.generative :refer (defspec)]))

(defn read
  [readable & options]
  (.readObject ^Reader (apply fress/reader (fress/to-input-stream readable) options)))

(defn ^ByteBuffer write
  [obj & options]
  (let [{:keys [footer?]} (when options (apply hash-map options))
        bos (BytesOutputStream.)
        writer ^Writer (apply fress/writer bos options)]
    (.writeObject writer obj)
    (when footer?
      (.writeFooter writer))
    (@#'fress/bytestream->buf bos)))

(defn roundtrip
  [x]
  (-> x write read))

(def clojure-fressianable
  gen/anything)

(defn gen-ratio
  []
  (Ratio. (gen/bigdec) (gen/bigdec)))

(def keyword-pool
  "Pool keywords so generation does not exhaust permgen"
  (delay
   (binding [gen/*rnd* (java.util.Random. 42)]
     (into [] (repeatedly 1000 gen/keyword)))))

(defn keyword-from-pool
  []
  (gen/rand-nth @keyword-pool))

(def symbol-pool
  "Pool symbols so generation does not exhaust permgen"
  (delay
   (binding [gen/*rnd* (java.util.Random. 42)]
     (into [] (repeatedly 1000 gen/symbol)))))

(defn symbol-from-pool
  []
  (gen/rand-nth @keyword-pool))

(defrecord ExampleRecord [^long f1 ^char f2 f3])

(defn gen-example-record
  []
  (ExampleRecord. (gen/long) (gen/char) (keyword-from-pool)))

(defn data-with-handler
  "Generate data that has data.fressian specific handler"
  []
  (gen/one-of gen/char
              gen/ratio
              (gen/vec gen/long)
              gen/bigint
              gen-example-record
              symbol-from-pool
              keyword-from-pool))

(defspec roundtrip-anything
  roundtrip
  [^{:tag `clojure-fressianable} o]
  (when-not (= o %)
    (throw (ex-info "object != readback" {:object o :readback %})))
  (when-not (= % o)
    (throw (ex-info "readback != object" {:object o :readback %})))
  ;; metadata not supported yet
  #_(when-not (= (meta o) (meta %))
      (throw (ex-info "(meta obj) != (meta readback)" {:object o :readback %}))))

(defspec roundtrip-handlers
  roundtrip
  [^{:tag `data-with-handler} o])


