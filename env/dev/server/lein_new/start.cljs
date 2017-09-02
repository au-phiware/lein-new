(ns ^:figwheel-always lein-new.start
  (:require [cljs.nodejs :as nodejs]
            [lein-new.env :refer [env]]
            [lein-new.core :refer [start-server]]))

(nodejs/enable-util-print!)

(.on js/process "uncaughtException" #(js/console.error %))

(set! *main-cli-fn* #(do (swap! env
                          (fn [& _] {:document-root "target/dev/client"}))
                        ))

