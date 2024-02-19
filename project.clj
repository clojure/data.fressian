(defproject org.clojure/data.fressian "0.2.1-SNAPSHOT"
  :description "Read/write Fressian from Clojure."
  :url "https://github.com/clojure/data.fressian"
  :license {:name "Eclipse Public License - v 1.0"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.fressian/fressian "0.6.6"]
                 [org.clojure/test.generative "1.1.1" :scope "test"]]
  :jvm-opts ["-Xmx2g" "-server"])
