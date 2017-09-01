(ns lein-new.start
  (:require
    [cljs.nodejs :as nodejs]
    [mount.core :as mount]
    [lein-new.core]
    #_[doo.runner :refer-macros [doo-tests]]
    #_[lein-new.core-test]))

#_(doo-tests 'lein-new.core-test)

(nodejs/enable-util-print!)

(let [doc-root (.resolve (nodejs/require "path")
                         js/__dirname ".." ".." "client")]
  (set! *main-cli-fn* #(-> (mount/swap
                            {#'lein-new.env/env
                             {:document-root doc-root}})
                          mount/start)))
