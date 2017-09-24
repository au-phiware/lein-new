(ns lein-new.core
  (:require [mount.core :refer [defstate]]
            [lein-new.hello :refer [hello]]
            [jquery]
            [popper.js]
            [bootstrap]
            [pushy.core :as pushy]))

(defn hello-world [world]
  (-> ".modal-content p" jquery (.text (hello world)))
  (-> ".modal" jquery .modal))

(defstate history
  :start (pushy/push-state! hello-world identity)
  :stop  (pushy/stop! @history))

