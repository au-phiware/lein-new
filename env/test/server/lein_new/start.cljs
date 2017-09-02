(ns lein-new.start
  (:require
    [cljs.nodejs :as nodejs]
    [lein-new.env :refer [env]]
    [lein-new.core :refer [start-server]]
    #_[doo.runner :refer-macros [doo-tests]]
    #_[lein-new.core-test]))

(.install (nodejs/require "source-map-support"))

#_(doo-tests 'lein-new.core-test)

(nodejs/enable-util-print!)

(let [doc-root (.resolve (nodejs/require "path")
                         js/__dirname ".." ".." "client")]
  (set! *main-cli-fn* #(do (swap! env
                                  (fn [& _]
                                    {:document-root doc-root}))
                           (start-server 3000))))
