(ns lein-new.server-core
  (:require [cljs.nodejs :as nodejs]
            [lein-new.hello :refer [hello]]
            [hiccups.runtime]
            [lein-new.env :refer [env]]
            [mount.core :refer [defstate]])
  (:require-macros [hiccups.core :refer [defhtml]]))

(defhtml master [app]
  [:html
   [:head
    [:title "Hello World!"]
    [:link {:rel "stylesheet" :href "/css/bootstrap.css"}]]
   [:body
    app
    [:div.modal.fade
     {:tabindex -1 :role "dialog" :aria-labelledby "mySmallModalLabel" :aria-hidden true}
     [:div.modal-dialog.modal-sm
      [:div.modal-content
       [:p]]]]
    [:script {:src "/js/out.js"}]]])

(defn hello-world [req res]
  (.send res (master [:h1 (hello "World!")])))

(defn start-server [port]
  (let [express (nodejs/require "express")]
    (-> (new express)
       (.use ((aget express "static") (:document-root @env)))
       (.get "/" hello-world)
       (.listen port #(println "Listening on port" port)))))

(defstate server
  :start (start-server 3000)
  :stop  (.close @server))

