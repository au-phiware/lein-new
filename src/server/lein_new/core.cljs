(ns lein-new.core
  (:require [cljs.nodejs :as nodejs]
            [lein-new.hello :refer [hello]]
            [hiccups.runtime]
            [lein-new.env :refer [env]])
  (:require-macros [hiccups.core :refer [defhtml]]))

(defhtml master [app]
  [:html
   [:head
    [:title "Hello World!"]]
   [:body
    app
    [:script {:src "/js/out.js"}]]])

(defn hello-world [req res]
  (.send res (master [:h1 (hello "World!")])))

(defn start-server [port]
  (let [express (nodejs/require "express")]
    (-> (express)
       (.use ((aget express "static") (:document-root @env)))
       (.get "/" hello-world)
       (.listen port #(println "Listening on port" port)))))

