(ns ^:figwheel-always lein-new.start
  (:require [cljs.nodejs :as nodejs]
            [mount.core :as mount]
            [lein-new.server-core]))

(nodejs/enable-util-print!)

(.on js/process "uncaughtException" #(js/console.error %))

(set! *main-cli-fn* #(-> (mount/swap
                          {#'lein-new.env/env {:document-root "target/dev/client"}})
                        mount/start))

