(ns datetime-logic.us-holidays
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic
    datetime-logic.gregorian)
  (:require
      [clojure.core.logic.fd :as fd]))

(defn- third-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 15 21))
    (== day day_)))

(defn- fourth-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 22 28))
    (== day day_)))

(defn thanksgiving [year month-num day]
  (fresh [month_ day_]
    (fourth-week day_)
    (== day day_)
    (== month_ 11)
    (== month-num month_)
    (day-of-the-week year month_ day_ :thursday)))

(defn new-years-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 1)
    (== day 1)))

(defn new-years-eve [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 12)
    (== day 31)))

(defn mlk-bday [year month-num day]
  (fresh [month_ day_]
    (third-week day_)
    (== day day_)
    (== month_ 1)
    (== month-num month_)
    (day-of-the-week year month_ day_ :monday)))

(defn washington-bday [year month-num day]
  (fresh [month_ day_]
    (third-week day_)
    (== day day_)
    (== month_ 2)
    (== month-num month_)
    (day-of-the-week year month_ day_ :monday)))