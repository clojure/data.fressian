{:namespaces
 ({:source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian/clojure.data.fressian-api.html",
   :name "clojure.data.fressian",
   :author "Stuart Halloway",
   :doc "Read/write fressian data. See http://www.edn-format.org/"}),
 :vars
 ({:arglists ([o]),
   :name "associative-lookup",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L52",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/associative-lookup",
   :doc "Build an ILookup from an associative collection.",
   :var-type "function",
   :line 52,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([writer]),
   :name "begin-closed-list",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L267",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/begin-closed-list",
   :doc
   "Begin writing a fressianed list.  To end the list, call end-list.\nUsed to write sequential data whose size is not known in advance.",
   :var-type "function",
   :line 267,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([writer]),
   :name "begin-open-list",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L278",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/begin-open-list",
   :doc
   "Advanced.  Writes fressian code to begin an open list.  An\nopen list can be terminated either by a call to end-list,\nor by simply closing the stream.  Used to write sequential\ndata whose size is not known in advance, in contexts where\nstream failure can safely be interpreted as end of list.",
   :var-type "function",
   :line 278,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:file "src/main/clojure/clojure/data/fressian.clj",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L152",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/clojure-read-handlers",
   :namespace "clojure.data.fressian",
   :line 152,
   :var-type "var",
   :doc "Standard set of read handlers for Clojure data.",
   :name "clojure-read-handlers"}
  {:file "src/main/clojure/clojure/data/fressian.clj",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L71",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/clojure-write-handlers",
   :namespace "clojure.data.fressian",
   :line 71,
   :var-type "var",
   :doc "Standard set of write handlers for Clojure data.",
   :name "clojure-write-handlers"}
  {:arglists ([in & {:keys [handlers checksum?]}]),
   :name "create-reader",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L201",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/create-reader",
   :doc
   "Create a fressian reader targeting in, which must be compatible\nwith clojure.java.io/input-stream.  Handlers must be a map of\ntag => ReadHandler wrapped in associative-lookup. See\nclojure-read-handlers for an example.",
   :var-type "function",
   :line 201,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([out & {:keys [handlers]}]),
   :name "create-writer",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L193",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/create-writer",
   :doc
   "Create a fressian writer targeting out. Handlers must be\na nested map of type => tag => WriteHandler wrapped with\nassociative-lookup and inheritance-lookup. See\nclojure-write-handlers for an example.",
   :var-type "function",
   :line 193,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([writer]),
   :name "end-list",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L273",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/end-list",
   :doc "Ends a list begun with begin-closed-list.",
   :var-type "function",
   :line 273,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([cache-pred]),
   :name "field-caching-writer",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L125",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/field-caching-writer",
   :doc
   "Returns a record writer that caches values for keys\nmatching cache-pred, which is typically specified\nas a set, e.g. (field-caching-writer #{:color})",
   :var-type "function",
   :line 125,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([lookup]),
   :name "inheritance-lookup",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L58",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/inheritance-lookup",
   :doc
   "Returns an inheritance aware lookup based on lookup that will match\nsubclasses as well as exact matches.  Will walk inheritance hierarchy\nonce per new type encountered to find the best match, then cache\nresults.",
   :var-type "function",
   :line 58,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([readable & options]),
   :name "read",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L221",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/read",
   :doc
   "Convenience method for reading a single fressian object.\nTakes same options as create-writer.  Readable can be\nany type supported by clojure.java.io/input-stream, or\na ByteBuffer.",
   :var-type "function",
   :line 221,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([rdr]),
   :name "read-object",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L211",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/read-object",
   :doc "Read a single object from a fressian reader.",
   :var-type "function",
   :line 211,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([obj]),
   :name "tag",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L250",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/tag",
   :doc "Returns the tag if object is a tagged-object, else nil.",
   :var-type "function",
   :line 250,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([o]),
   :name "tagged-object?",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L243",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/tagged-object?",
   :doc
   "Returns true if o is a tagged object, which will occur when\nthe reader does not recognized a specific type.  Use tag\nand tagged-value to access the contents of a tagged-object.",
   :var-type "function",
   :line 243,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([obj]),
   :name "tagged-value",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L256",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/tagged-value",
   :doc
   "Returns the value (an Object arrray) wrapped by obj, or nil\nif obj is not a tagged object.",
   :var-type "function",
   :line 256,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([obj & options]),
   :name "write",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L229",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/write",
   :doc
   "Convenience method for writing a single object.  Returns a\nbyte buffer.  Options are the same as for create-reader,\nwith one additional option.  If footer? is specified, will\nwrite a fressian footer after writing the object.",
   :var-type "function",
   :line 229,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:arglists ([writer obj]),
   :name "write-object",
   :namespace "clojure.data.fressian",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L216",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/write-object",
   :doc
   "Write a single object to a fressian reader. Returns the reader.",
   :var-type "function",
   :line 216,
   :file "src/main/clojure/clojure/data/fressian.clj"}
  {:file "src/main/clojure/clojure/data/fressian.clj",
   :raw-source-url
   "https://github.com/clojure/data.fressian/raw/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj",
   :source-url
   "https://github.com/clojure/data.fressian/blob/73defb8fe500d9a976e0d38a8300b9c72fe2eb98/src/main/clojure/clojure/data/fressian.clj#L38",
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/FressianReadable",
   :namespace "clojure.data.fressian",
   :line 38,
   :var-type "protocol",
   :doc nil,
   :name "FressianReadable"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/data.fressian//clojure.data.fressian-api.html#clojure.data.fressian/to-input-stream",
   :namespace "clojure.data.fressian",
   :var-type "function",
   :arglists ([obj]),
   :doc "Implementation detail.",
   :name "to-input-stream"})}
