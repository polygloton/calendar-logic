(ns datetime-logic.us-holidays
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic
    datetime-logic.gregorian)
  (:require
      [clojure.core.logic.fd :as fd]))

; http://www.usa.gov/citizens/holidays.shtml

(defn- first-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 1 7))
    (== day day_)))

(defn- second-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 8 14))
    (== day day_)))

(defn- third-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 15 21))
    (== day day_)))

(defn- fourth-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 22 28))
    (== day day_)))

(defn- fifth-week [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 29 31))
    (== day day_)))

(defn- last-week-31 [day]
  (fresh [day_]
    (fd/in day_ (fd/interval 25 31))
    (== day day_)))

(defn new-years-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 1)
    (== day 1)))

(defn mlk-bday [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (third-week day_)
    (== day day_)
    (== month_ 1)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn washington-bday [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (third-week day_)
    (== day day_)
    (== month_ 2)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn memorial-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (last-week-31 day_)
    (== day day_)
    (== month_ 5)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn independence-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 7)
    (== day 4)))

(defn labor-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (first-week day_)
    (== day day_)
    (== month_ 9)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :monday)))

(defn columbus-day [year month-num day]
  (fresh [year_ month_ day_]
      (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
      (== year year_)
      (second-week day_)
      (== day day_)
      (== month_ 10)
      (== month-num month_)
      (day-of-the-week year_ month_ day_ :monday)))

(defn veterans-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 11)
    (== day 11)))

(defn thanksgiving-day [year month-num day]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (fourth-week day_)
    (== day day_)
    (== month_ 11)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :thursday)))

(defn christmas-day [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 12)
    (== day 25)))

(defn new-years-eve [year month-num day]
  (fresh [year_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year year_)
    (== month-num 12)
    (== day 31)))

(defn federal-holiday [year month-num day holiday]
  (fresh [year_ month_ day_]
    (fd/in year_ (fd/interval 1 Integer/MAX_VALUE))
    (== year_ year)
    (fd/in month_ (fd/interval 1 12))
    (== month_ month-num)
    (fd/in day_ (fd/interval 1 31))
    (== day_ day)
    (conde
      [(new-years-day year_ month_ day_) (== holiday :new-years-day)]
      [(mlk-bday year_ month_ day_) (== holiday :mlk-bday)]
      [(washington-bday year_ month_ day_) (== holiday :washington-bday)]
      [(memorial-day year_ month_ day_) (== holiday :memorial-day)]
      [(independence-day year_ month_ day_) (== holiday :independence-day)]
      [(labor-day year_ month_ day_) (== holiday :labor-day)]
      [(columbus-day year_ month_ day_) (== holiday :columbus-day)]
      [(veterans-day year_ month_ day_) (== holiday :veterans-day)]
      [(thanksgiving-day year_ month_ day_) (== holiday :thanksgiving-day)]
      [(christmas-day year_ month_ day_) (== holiday :christmas-day)]
      )))