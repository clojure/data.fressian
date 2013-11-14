data.fressian
========================================

Read and write fressian data. See https://github.com/Datomic/fressian/wiki

Releases and Dependency Information
----------------------------------------

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

    [org.clojure/data.fressian "0.2.0"]

[Maven](http://maven.apache.org/) dependency information:

    <dependency>
      <groupId>org.clojure</groupId>
      <artifactId>data.fressian</artifactId>
      <version>0.2.0</version>
    </dependency>

Other versions:

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22data.fressian%22)

* [Development Snapshots](https://oss.sonatype.org/index.html#nexus-search;gav~org.clojure~data.fressian~~~)

* [Development Snapshot Repositories](http://dev.clojure.org/display/doc/Maven+Settings+and+Repositories)



Usage
----------------------------------------

[API Documentation](http://clojure.github.com/data.fressian/)

Examples:

    (require '[clojure.data.fressian :as fress])

    ;; read / write objects
    (fress/write x)
    (fress/read)

    ;; create reader/write on input/output streams
    (fress/create-reader is)
    (fress/create-writer os)

    ;; all read/write functions tate optional handlers
    (fress/write x :handlers my-handlers)

Other options are available. Refer to the [API Documentation](http://clojure.github.com/data.fressian/) for details.


Developer Information
----------------------------------------

* [GitHub project](https://github.com/clojure/data.fressian)

* [Bug Tracker](http://dev.clojure.org/jira/browse/DFRESS)

* [Continuous Integration](http://build.clojure.org/job/data.fressian/)

* [Compatibility Test Matrix](http://build.clojure.org/job/data.fressian-test-matrix/)



Change Log
----------------------------------------


Copyright and License
----------------------------------------

Copyright (c) Cognitect, Inc. All rights reserved.  The use and
distribution terms for this software are covered by the Eclipse Public
License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) which can
be found in the file epl-v10.html at the root of this distribution.
By using this software in any fashion, you are agreeing to be bound by
the terms of this license.  You must not remove this notice, or any
other, from this software.
