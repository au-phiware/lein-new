(ns lein-new.app
  (:require [mount.core :as mount]
            [lein-new.core]))

(enable-console-print!)

(.addeventlistener js/window "error" #(js/console.error %))

(mount/start)

