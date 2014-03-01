(ns calendar-logic.us-holidays
  (:refer-clojure :exclude [==])
  (:use clojure.core.logic)
  (:require [calendar-logic.gregorian :refer :all :as greg]
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

(defn- before-last-week-31 [day]
  (fresh [day']
         (fd/in day' (fd/interval 1 24))
         (== day day')))

(defn- last-week-30 [day]
  (fresh [day_]
         (fd/in day_ (fd/interval 24 30))
         (== day day_)))

(defn- before-last-week-30 [day]
  (fresh [day']
         (fd/in day' (fd/interval 1 23))
         (== day day')))

(defn- min-max [q]
  (fd/in q (fd/interval 1 Integer/MAX_VALUE)))

(defn new-years-day [year month-num day]
  (fresh [year_]
         (min-max year_)
         (== year year_)
         (== month-num 1)
         (== day 1)))

(defn not-new-years-day [year month-num day]
  (fresh [year_]
         (min-max year_)
         (== year year_)
         (!= [month-num day] [1 1])
         (greg/day-in-month year month-num day)))

(defn mlk-bday [year month-num day]
  (fresh [year' day']
         (min-max year')
         (== year year')
         (third-week day')
         (== day day')
         (== month-num 1)
         (greg/day-of-the-week year month-num day :monday)))

(defn not-mlk-bday [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(first-week day)]
                 [(second-week day)]
                 [(third-week day) (!= [month-num dow] [1 :monday])]
                 [(fourth-week day)]
                 [(fifth-week day)]))))

(defn groundhog-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (== month-num 2)
         (== day 2)))

(defn not-groundhog-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (!= [month-num day] [2 2])
         (greg/day-in-month year month-num day)))

(defn valentines-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (== month-num 2)
         (== day 14)))

(defn not-valentines-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (!= [month-num day] [2 14])
         (greg/day-in-month year month-num day)))

(defn washington-bday [year month-num day]
  (fresh [year' day']
         (min-max year')
         (== year year')
         (third-week day')
         (== day day')
         (== month-num 2)
         (greg/day-of-the-week year month-num day :monday)))

(defn not-washington-bday [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(first-week day)]
                 [(second-week day)]
                 [(third-week day) (!= [month-num dow] [2 :monday])]
                 [(fourth-week day)]
                 [(fifth-week day)]))))

(defn earth-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (== month-num 4)
         (== day 22)))

(defn not-earth-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (!= [month-num day] [4 22])
         (greg/day-in-month year month-num day)))

(defn arbor-day [year month-num day]
  (fresh [year' day']
         (min-max year')
         (== year year')
         (last-week-30 day')
         (== day day')
         (== month-num 4)
         (day-of-the-week year month-num day :friday)))

(defn not-arbor-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(before-last-week-30 day)]
                 [(last-week-30 day) (!= [month-num dow] [4 :friday])]))))

(defn mothers-day [year month-num day]
  (fresh [year' day']
         (min-max year')
         (== year year')
         (second-week day')
         (== day day')
         (== month-num 5)
         (greg/day-of-the-week year month-num day :sunday)))

(defn not-mothers-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(first-week day)]
                 [(second-week day) (!= [month-num dow] [5 :sunday])]
                 [(third-week day)]
                 [(fourth-week day)]
                 [(fifth-week day)]))))

(defn memorial-day [year month-num day]
  (fresh [year' day']
         (min-max year')
         (== year year')
         (last-week-31 day')
         (== day day')
         (== month-num 5)
         (day-of-the-week year month-num day :monday)))

(defn not-memorial-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(before-last-week-31 day)]
                 [(last-week-31 day) (!= [month-num dow] [5 :monday])]))))

(defn flag-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (== month-num 6)
         (== day 14)))

(defn not-flag-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (!= [month-num day] [6 14])
         (greg/day-in-month year month-num day)))

(defn fathers-day [year month-num day]
  (fresh [year' day']
    (min-max year')
    (== year year')
    (third-week day')
    (== day day')
    (== month-num 6)
    (day-of-the-week year month-num day :sunday)))

(defn not-fathers-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(first-week day)]
                 [(second-week day)]
                 [(third-week day) (!= [month-num dow] [6 :sunday])]
                 [(fourth-week day)]
                 [(fifth-week day)]))))

(defn independence-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (== month-num 7)
         (== day 4)))

(defn not-independence-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (!= [month-num day] [7 4])
         (greg/day-in-month year month-num day)))

(defn labor-day [year month-num day]
  (fresh [year' day']
         (min-max year')
         (== year year')
         (== month-num 9)
         (first-week day')
         (== day day')
         (day-of-the-week year month-num day :monday)))

(defn not-labor-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(first-week day) (!= [month-num dow] [9 :monday])]
                 [(second-week day)]
                 [(third-week day)]
                 [(fourth-week day)]
                 [(fifth-week day)]))))

(defn patriot-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (== month-num 9)
         (== day 11)))

(defn not-patriot-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (!= [month-num day] [9 11])
         (greg/day-in-month year month-num day)))

(defn columbus-day [year month-num day]
  (fresh [year' day']
         (min-max year')
         (== year year')
         (second-week day')
         (== day day')
         (== month-num 10)
         (day-of-the-week year month-num day :monday)))

(defn not-columbus-day [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (greg/day-in-month year month-num day)
         (fresh [dow]
                (greg/day-of-the-week year month-num day dow)
                (conde
                 [(first-week day)]
                 [(second-week day) (!= [month-num dow] [10 :monday])]
                 [(third-week day)]
                 [(fourth-week day)]
                 [(fifth-week day)]))))

(defn halloween [year month-num day]
  (fresh [year']
         (min-max year')
         (== year year')
         (== month-num 10)
         (== day 31)))

(defn not-halloween [year month-num day]
  (fresh [year']
         (min-max year')
         (!= [month-num day] [10 31])
         (greg/day-in-month year month-num day)))

(defn veterans-day [year month-num day]
  (fresh [year_]
    (min-max year_)
    (== year year_)
    (== month-num 11)
    (== day 11)))

(defn thanksgiving-day [year month-num day]
  (fresh [year_ month_ day_]
    (min-max year_)
    (== year year_)
    (fourth-week day_)
    (== day day_)
    (== month_ 11)
    (== month-num month_)
    (day-of-the-week year_ month_ day_ :thursday)))

(defn pearl-harbor-day [year month-num day]
  (fresh [year_]
    (min-max year_)
    (== year year_)
    (== month-num 12)
    (== day 7)))

(defn christmas-eve [year month-num day]
  (fresh [year_]
    (min-max year_)
    (== year year_)
    (== month-num 12)
    (== day 24)))

(defn christmas-day [year month-num day]
  (fresh [year_]
    (min-max year_)
    (== year year_)
    (== month-num 12)
    (== day 25)))

(defn new-years-eve [year month-num day]
  (fresh [year_]
    (min-max year_)
    (== year year_)
    (== month-num 12)
    (== day 31)))

(defn federal-holiday [year month-num day holiday]
  (fresh [year_ month_ day_]
    (min-max year_)
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
      [(christmas-day year_ month_ day_) (== holiday :christmas-day)])))
