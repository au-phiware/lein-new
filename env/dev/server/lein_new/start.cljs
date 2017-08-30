(ns ^:figwheel-always lein-new.start
  (:require [cljs.nodejs :as nodejs]
            [lein-new.core]))

(nodejs/enable-util-print!)

(.on js/process "uncaughtException" #(js/console.error %))

(set! *main-cli-fn* mount/start)

