(ns lein-new.app
  (:require [lein-new.core]))

(enable-console-print!)

(.addEventListener js/window "error" #(js/console.error %))

