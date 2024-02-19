# data.fressian

Read and write fressian data. See https://github.com/Datomic/fressian/wiki

# Releases and Dependency Information

[CLI/`deps.edn`](https://clojure.org/reference/deps_edn) dependency information:
```clojure
org.clojure/data.fressian {:mvn/version "1.1.0"}
```

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

    [org.clojure/data.fressian "1.1.0"]

[Maven](http://maven.apache.org/) dependency information:

    <dependency>
      <groupId>org.clojure</groupId>
      <artifactId>data.fressian</artifactId>
      <version>1.1.0</version>
    </dependency>

Other versions:

* [All Released Versions](https://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22data.fressian%22)
* [Development Snapshots](https://oss.sonatype.org/index.html#nexus-search;gav~org.clojure~data.fressian~~~)
* [Development Snapshot Repositories](https://dev.clojure.org/display/doc/Maven+Settings+and+Repositories)

# Usage

[API Documentation](https://clojure.github.com/data.fressian/)

Basic examples:

```clojure
(require '[clojure.data.fressian :as fress])

;; read / write objects
(fress/write x)
(fress/read)

;; create reader/write on input/output streams
(fress/create-reader is)
(fress/create-writer os)
```

It is also possible to create custom tags and handlers. For more information see [Creating custom handlers](https://github.com/clojure/data.fressian/wiki/Creating-custom-handlers) for details.

# Developer Information

* [GitHub project](https://github.com/clojure/data.fressian)
* [Bug Tracker](https://clojure.atlassian.net/browse/DFRS)
* [Continuous Integration](https://github.com/clojure/data.fressian/actions/workflows/test.yml)


# Copyright and License

Copyright (c) Rich Hickey, Nu North America, Inc. All rights reserved.  The use and
distribution terms for this software are covered by the Eclipse Public
License 1.0 (https://opensource.org/license/epl-1-0/) witch can
be found in the file epl-v10.html at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by
the terms of this license.  You must not remove this notice, or any
other, from this software.
