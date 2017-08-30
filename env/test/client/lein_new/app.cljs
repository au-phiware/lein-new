(ns lein-new.app
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [lein-new.core-test]))

(doo-tests 'lein-new.core-test)

