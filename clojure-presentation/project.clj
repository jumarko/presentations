(defproject clojure-presentation "0.1.0-SNAPSHOT"
  :description "Clojure: A functional LISP on the JVM (FPBrno)"
  :url "https://github.com/jumarko/presentations/clojure-presentation"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-beta1"]
                 [org.clojure/core.async "0.3.443"]]
  :main ^:skip-aot clojure-presentation.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
