(ns datetime-logic.us-holidays
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic
    datetime-logic.gregorian)
  (:require
      [clojure.core.logic.fd :as fd]))

(defn thanksgiving [year month-num day]
  (fresh [month_ day_]
    (fd/in day_ (fd/interval 22 28))
    (== month_ 11)
    (day-of-the-weeko year month_ day_ :thursday)
    (== day day_)
    (== month-num month_)))