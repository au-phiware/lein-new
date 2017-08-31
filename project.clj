(defproject lein-new "0.1.0-SNAPSHOT"
  :description "Minimal lein project with multiple environments."
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.908"
                  :scope "provided"]
                 [mount "0.1.11"]
                 [kibu/pushy "0.3.8"]
                 [hiccups "0.3.0"]
                 [doo "0.1.7"]]
  :plugins [[lein-cljsbuild "1.1.7"
             :exclusions [[org.clojure/clojure]]]
            [lein-npm "0.6.2"]
            [lein-doo "0.1.7"]
            [lein-figwheel "0.5.13"]]
  :target-path "target"
  :source-paths []
  :clean-targets ^{:protect false} [:target-path]
  :resource-paths ["resources"]
  :npm {:dependencies [[express "4.15.4"]
                       [serve-static "1.12.4"]]
        :devDependencies [["@cljs-oss/module-deps" "1.1.1"]
                          [ws "3.1.0"]
                          [source-map-support "0.4.16"]]}
  :cljsbuild
  {:builds {:start
            {:source-paths ["src/server" "src/common"]
             :compiler
             {:main lein-new.start
              :output-dir "target/dev/server/js/out"
              :output-to  "target/dev/server/js/out.js"
              :target :nodejs}}
            :app
            {:source-paths ["src/client" "src/common"]
             :compiler
             {:main lein-new.app
              :output-dir "target/dev/client/js/out"
              :output-to  "target/dev/client/js/out.js"
              :asset-path "js/out"
              :process-shim false
              :language-out :ecmascript5-strict}}}}
  :figwheel {:builds-to-start [:start :app]}
  :profiles
  {:dev
   {:main "target/dev/server/js/out.js"
    :source-paths ["env/dev"]
    :resource-paths ["target/dev/client"]
    :npm {:package {:main "target/dev/server/js/out.js"
                    :scripts {:start "node target/dev/server/js/out.js"}}}
    :cljsbuild {:builds 
                {:start {:source-paths ["env/dev/server"]
                         :figwheel true
                         :compiler
                         {:optimizations :none
                          :pretty-print true
                          :source-map true
                          :source-map-timestamp true}}
                 :app {:source-paths ["env/dev/client"]
                       :figwheel true
                       :compiler
                       {:optimizations :none
                        :pretty-print true
                        :source-map true
                        :source-map-timestamp true}}}}}
   :prod
   {:main "target/prod/server/js/out.js"
    :source-paths ["env/prod"]
    :resource-paths ["target/prod/client"]
    :npm {:package {:main "target/prod/server/js/out.js"
                    :scripts {:start "node target/prod/server/js/out.js"}}}
    :cljsbuild {:builds
                {:start {:source-paths ["env/prod/server"]
                         :compiler
                         {:output-dir "target/prod/server/js/out"
                          :output-to  "target/prod/server/js/out.js"
                          :optimizations :advanced
                          :source-map false
                          :pretty-print false}}
                 :app {:source-paths ["env/prod/client"]
                       :compiler
                       {:output-dir "target/prod/client/js/out"
                        :output-to  "target/prod/client/js/out.js"
                        :optimizations :advanced
                        :source-map false
                        :pretty-print false}}}}}
   :test
   [:prod
    {:main "target/test/server/js/out.js"
     :resource-paths ["target/test/client"]
     :doo {:build "test"}
     :npm {:package {:main "target/test/server/js/out.js"
                     :scripts {:start "node target/test/server/js/out.js"}}}
     :cljsbuild
     {:builds {:start {:source-paths ^:replace ["src/server" "src/common"
                                                "test/server" "env/test/server"]
                       :compiler
                       {:output-dir "target/test/server/js/out"
                        :output-to  "target/test/server/js/out.js"}}
               :app {:source-paths ^:replace ["src/client" "src/common"
                                              "test/client" "env/test/client"]
                     :compiler
                     {:output-dir "target/test/client/js/out"
                      :output-to  "target/test/client/js/out.js"}}}}}]}
  :aliases
  {"build" ["do"
            ["npm" "install"]
            ["figwheel"]]
   "start" ["npm" "start"]})
