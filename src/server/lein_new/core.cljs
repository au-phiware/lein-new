(ns lein-new.core
  (:require [cljs.nodejs :as nodejs]
            [lein-new.hello :refer [hello]]
            [mount.core :refer [defstate]]))

(defn hello-world [req res]
  (.send res (hello "World!")))

(defn start-server [port]
  (-> ((nodejs/require "express"))
      (.get "/" hello-world)
      (.listen port #(println "Listening on port" port))))

(defstate server
  :start (start-server 3000)
  :stop  (.close @server))

