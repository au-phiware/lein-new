(ns lein-new.start
  (:require [cljs.nodejs :as nodejs]
            [lein-new.core]))

(set! *main-cli-fn* mount/start)

