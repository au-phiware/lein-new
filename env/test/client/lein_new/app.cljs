(ns lein-new.app
  (:require
    [mount.core :as mount]
    [lein-new.core]
    #_[doo.runner :refer-macros [doo-tests]]
    #_[lein-new.core-test]))

#_(doo-tests 'lein-new.core-test)

(enable-console-print!)

(mount/start)

