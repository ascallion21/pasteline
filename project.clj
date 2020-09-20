(defproject pasteline "0.1.0-SNAPSHOT"
  :description "Binds ctrl-alt-v to paste w/o newlines"
  :url ""
  :license {:name "MIT"
            :url "https://github.com/gscanlon21/pasteline/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                [org.clojars.houshuang/keymaster-clj "0.1.0"]]
  :main ^:skip-aot pasteline.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
