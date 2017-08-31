(ns lein-new.env
  (:require [mount.core :refer [defstate]]))

(defstate env
  :start {:document-root "."})
