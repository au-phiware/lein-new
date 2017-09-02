(ns lein-new.start
  (:require [cljs.nodejs :as nodejs]
            [lein-new.env :refer [env]]
            [lein-new.core :refer [start-server]]))

(nodejs/enable-util-print!)

(let [doc-root (.resolve (nodejs/require "path")
                         js/__dirname ".." ".." "client")]
  (set! *main-cli-fn* #(do (swap! env
                                  (fn [& _]
                                    {:document-root doc-root}))
                           (start-server 3000))))

