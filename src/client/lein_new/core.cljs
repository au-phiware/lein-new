(ns lein-new.core
  (:require [lein-new.hello :refer [hello]]
            [pushy.core :as pushy]))

(defn hello-world [world] (js/alert (hello world)))

(def history (pushy/push-state! hello-world identity))

