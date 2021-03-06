(ns calendar-logic.examples.happy-fun-days
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic :exclude [is]]
        [clojure.test]
        [calendar-logic.test.test-helper])
  (:require [calendar-logic.us-holidays :as hol]
            [calendar-logic.gregorian :as greg]
            [clojure.core.logic.fd :as fd]
            [calendar-logic.examples.happy-fun-days :refer :all]))

(defn happy-fun-days [year_]
  (run* [year month day dow]
        (== year year_)
        (greg/day-in-month year month day)
        (greg/day-of-the-week year month day dow)
        (fresh [holiday]
               (conde
                [(greg/weekend dow)]
                [(greg/weekday dow) (hol/federal-holiday year month day holiday)]))))

(deftest ^:example test-happy-fun-days-in-2014
  (eg (sort
       (happy-fun-days 2014))
      [[2014 1 1 :wednesday] [2014 1 4 :saturday] [2014 1 5 :sunday]
       [2014 1 11 :saturday] [2014 1 12 :sunday] [2014 1 18 :saturday]
       [2014 1 19 :sunday] [2014 1 20 :monday] [2014 1 25 :saturday]
       [2014 1 26 :sunday] [2014 2 1 :saturday] [2014 2 2 :sunday]
       [2014 2 8 :saturday] [2014 2 9 :sunday] [2014 2 15 :saturday]
       [2014 2 16 :sunday] [2014 2 17 :monday] [2014 2 22 :saturday]
       [2014 2 23 :sunday] [2014 3 1 :saturday] [2014 3 2 :sunday]
       [2014 3 8 :saturday] [2014 3 9 :sunday] [2014 3 15 :saturday]
       [2014 3 16 :sunday] [2014 3 22 :saturday] [2014 3 23 :sunday]
       [2014 3 29 :saturday] [2014 3 30 :sunday] [2014 4 5 :saturday]
       [2014 4 6 :sunday] [2014 4 12 :saturday] [2014 4 13 :sunday]
       [2014 4 19 :saturday] [2014 4 20 :sunday] [2014 4 26 :saturday]
       [2014 4 27 :sunday] [2014 5 3 :saturday] [2014 5 4 :sunday]
       [2014 5 10 :saturday] [2014 5 11 :sunday] [2014 5 17 :saturday]
       [2014 5 18 :sunday] [2014 5 24 :saturday] [2014 5 25 :sunday]
       [2014 5 26 :monday] [2014 5 31 :saturday] [2014 6 1 :sunday]
       [2014 6 7 :saturday] [2014 6 8 :sunday] [2014 6 14 :saturday]
       [2014 6 15 :sunday] [2014 6 21 :saturday] [2014 6 22 :sunday]
       [2014 6 28 :saturday] [2014 6 29 :sunday] [2014 7 4 :friday]
       [2014 7 5 :saturday] [2014 7 6 :sunday] [2014 7 12 :saturday]
       [2014 7 13 :sunday] [2014 7 19 :saturday] [2014 7 20 :sunday]
       [2014 7 26 :saturday] [2014 7 27 :sunday] [2014 8 2 :saturday]
       [2014 8 3 :sunday] [2014 8 9 :saturday] [2014 8 10 :sunday]
       [2014 8 16 :saturday] [2014 8 17 :sunday] [2014 8 23 :saturday]
       [2014 8 24 :sunday] [2014 8 30 :saturday] [2014 8 31 :sunday]
       [2014 9 1 :monday] [2014 9 6 :saturday] [2014 9 7 :sunday]
       [2014 9 13 :saturday] [2014 9 14 :sunday] [2014 9 20 :saturday]
       [2014 9 21 :sunday] [2014 9 27 :saturday] [2014 9 28 :sunday]
       [2014 10 4 :saturday] [2014 10 5 :sunday] [2014 10 11 :saturday]
       [2014 10 12 :sunday] [2014 10 13 :monday] [2014 10 18 :saturday]
       [2014 10 19 :sunday] [2014 10 25 :saturday] [2014 10 26 :sunday]
       [2014 11 1 :saturday] [2014 11 2 :sunday] [2014 11 8 :saturday]
       [2014 11 9 :sunday] [2014 11 11 :tuesday] [2014 11 15 :saturday]
       [2014 11 16 :sunday] [2014 11 22 :saturday] [2014 11 23 :sunday]
       [2014 11 27 :thursday] [2014 11 29 :saturday] [2014 11 30 :sunday]
       [2014 12 6 :saturday] [2014 12 7 :sunday] [2014 12 13 :saturday]
       [2014 12 14 :sunday] [2014 12 20 :saturday] [2014 12 21 :sunday]
       [2014 12 25 :thursday] [2014 12 27 :saturday] [2014 12 28 :sunday]]))

(defn business-days [year_]
  (run* [year month day dow]
        (== year year_)
        (greg/weekday dow)
        (!= [month day] [1 1])
        (!= [month day] [7 4])
        (!= [month day] [11 11])
        (!= [month day] [12 25])
        (greg/day-in-month year month day)
        (greg/day-of-the-week year month day dow)
        (conde
         [(== month 5) (conde
                        [(hol/before-last-week-31 day)]
                        [(hol/last-week-31 day)
                         (!= dow :monday)])]
         [(!= month 5) (conde
                        [(hol/first-week day) (!= [month dow] [9 :monday])]
                        [(hol/second-week day)
                         (!= [month dow] [10 :monday])]
                        [(hol/third-week day)
                         (!= [month dow] [1 :monday])
                         (!= [month dow] [2 :monday])]
                        [(hol/fourth-week day) (!= [month dow] [11 :thursday])]
                        [(hol/fifth-week day)])])))

(deftest ^:example test-business-days-in-2014
  (eg (sort
       (business-days 2014))
      [[2014 1 2 :thursday] [2014 1 3 :friday] [2014 1 6 :monday]
       [2014 1 7 :tuesday] [2014 1 8 :wednesday] [2014 1 9 :thursday]
       [2014 1 10 :friday] [2014 1 13 :monday] [2014 1 14 :tuesday]
       [2014 1 15 :wednesday] [2014 1 16 :thursday] [2014 1 17 :friday]
       [2014 1 21 :tuesday] [2014 1 22 :wednesday] [2014 1 23 :thursday]
       [2014 1 24 :friday] [2014 1 27 :monday] [2014 1 28 :tuesday]
       [2014 1 29 :wednesday] [2014 1 30 :thursday] [2014 1 31 :friday]
       [2014 2 3 :monday] [2014 2 4 :tuesday] [2014 2 5 :wednesday]
       [2014 2 6 :thursday] [2014 2 7 :friday] [2014 2 10 :monday]
       [2014 2 11 :tuesday] [2014 2 12 :wednesday] [2014 2 13 :thursday]
       [2014 2 14 :friday] [2014 2 18 :tuesday] [2014 2 19 :wednesday]
       [2014 2 20 :thursday] [2014 2 21 :friday] [2014 2 24 :monday]
       [2014 2 25 :tuesday] [2014 2 26 :wednesday] [2014 2 27 :thursday]
       [2014 2 28 :friday] [2014 3 3 :monday] [2014 3 4 :tuesday]
       [2014 3 5 :wednesday] [2014 3 6 :thursday] [2014 3 7 :friday]
       [2014 3 10 :monday] [2014 3 11 :tuesday] [2014 3 12 :wednesday]
       [2014 3 13 :thursday] [2014 3 14 :friday] [2014 3 17 :monday]
       [2014 3 18 :tuesday] [2014 3 19 :wednesday] [2014 3 20 :thursday]
       [2014 3 21 :friday] [2014 3 24 :monday] [2014 3 25 :tuesday]
       [2014 3 26 :wednesday] [2014 3 27 :thursday] [2014 3 28 :friday]
       [2014 3 31 :monday] [2014 4 1 :tuesday] [2014 4 2 :wednesday]
       [2014 4 3 :thursday] [2014 4 4 :friday] [2014 4 7 :monday]
       [2014 4 8 :tuesday] [2014 4 9 :wednesday] [2014 4 10 :thursday]
       [2014 4 11 :friday] [2014 4 14 :monday] [2014 4 15 :tuesday]
       [2014 4 16 :wednesday] [2014 4 17 :thursday] [2014 4 18 :friday]
       [2014 4 21 :monday] [2014 4 22 :tuesday] [2014 4 23 :wednesday]
       [2014 4 24 :thursday] [2014 4 25 :friday] [2014 4 28 :monday]
       [2014 4 29 :tuesday] [2014 4 30 :wednesday] [2014 5 1 :thursday]
       [2014 5 2 :friday] [2014 5 5 :monday] [2014 5 6 :tuesday]
       [2014 5 7 :wednesday] [2014 5 8 :thursday] [2014 5 9 :friday]
       [2014 5 12 :monday] [2014 5 13 :tuesday] [2014 5 14 :wednesday]
       [2014 5 15 :thursday] [2014 5 16 :friday] [2014 5 19 :monday]
       [2014 5 20 :tuesday] [2014 5 21 :wednesday] [2014 5 22 :thursday]
       [2014 5 23 :friday] [2014 5 27 :tuesday] [2014 5 28 :wednesday]
       [2014 5 29 :thursday] [2014 5 30 :friday] [2014 6 2 :monday]
       [2014 6 3 :tuesday] [2014 6 4 :wednesday] [2014 6 5 :thursday]
       [2014 6 6 :friday] [2014 6 9 :monday] [2014 6 10 :tuesday]
       [2014 6 11 :wednesday] [2014 6 12 :thursday] [2014 6 13 :friday]
       [2014 6 16 :monday] [2014 6 17 :tuesday] [2014 6 18 :wednesday]
       [2014 6 19 :thursday] [2014 6 20 :friday] [2014 6 23 :monday]
       [2014 6 24 :tuesday] [2014 6 25 :wednesday] [2014 6 26 :thursday]
       [2014 6 27 :friday] [2014 6 30 :monday] [2014 7 1 :tuesday]
       [2014 7 2 :wednesday] [2014 7 3 :thursday] [2014 7 7 :monday]
       [2014 7 8 :tuesday] [2014 7 9 :wednesday] [2014 7 10 :thursday]
       [2014 7 11 :friday] [2014 7 14 :monday] [2014 7 15 :tuesday]
       [2014 7 16 :wednesday] [2014 7 17 :thursday] [2014 7 18 :friday]
       [2014 7 21 :monday] [2014 7 22 :tuesday] [2014 7 23 :wednesday]
       [2014 7 24 :thursday] [2014 7 25 :friday] [2014 7 28 :monday]
       [2014 7 29 :tuesday] [2014 7 30 :wednesday] [2014 7 31 :thursday]
       [2014 8 1 :friday] [2014 8 4 :monday] [2014 8 5 :tuesday]
       [2014 8 6 :wednesday] [2014 8 7 :thursday] [2014 8 8 :friday]
       [2014 8 11 :monday] [2014 8 12 :tuesday] [2014 8 13 :wednesday]
       [2014 8 14 :thursday] [2014 8 15 :friday] [2014 8 18 :monday]
       [2014 8 19 :tuesday] [2014 8 20 :wednesday] [2014 8 21 :thursday]
       [2014 8 22 :friday] [2014 8 25 :monday] [2014 8 26 :tuesday]
       [2014 8 27 :wednesday] [2014 8 28 :thursday] [2014 8 29 :friday]
       [2014 9 2 :tuesday] [2014 9 3 :wednesday] [2014 9 4 :thursday]
       [2014 9 5 :friday] [2014 9 8 :monday] [2014 9 9 :tuesday]
       [2014 9 10 :wednesday] [2014 9 11 :thursday] [2014 9 12 :friday]
       [2014 9 15 :monday] [2014 9 16 :tuesday] [2014 9 17 :wednesday]
       [2014 9 18 :thursday] [2014 9 19 :friday] [2014 9 22 :monday]
       [2014 9 23 :tuesday] [2014 9 24 :wednesday] [2014 9 25 :thursday]
       [2014 9 26 :friday] [2014 9 29 :monday] [2014 9 30 :tuesday]
       [2014 10 1 :wednesday] [2014 10 2 :thursday] [2014 10 3 :friday]
       [2014 10 6 :monday] [2014 10 7 :tuesday] [2014 10 8 :wednesday]
       [2014 10 9 :thursday] [2014 10 10 :friday] [2014 10 14 :tuesday]
       [2014 10 15 :wednesday] [2014 10 16 :thursday] [2014 10 17 :friday]
       [2014 10 20 :monday] [2014 10 21 :tuesday] [2014 10 22 :wednesday]
       [2014 10 23 :thursday] [2014 10 24 :friday] [2014 10 27 :monday]
       [2014 10 28 :tuesday] [2014 10 29 :wednesday] [2014 10 30 :thursday]
       [2014 10 31 :friday] [2014 11 3 :monday] [2014 11 4 :tuesday]
       [2014 11 5 :wednesday] [2014 11 6 :thursday] [2014 11 7 :friday]
       [2014 11 10 :monday] [2014 11 12 :wednesday] [2014 11 13 :thursday]
       [2014 11 14 :friday] [2014 11 17 :monday] [2014 11 18 :tuesday]
       [2014 11 19 :wednesday] [2014 11 20 :thursday] [2014 11 21 :friday]
       [2014 11 24 :monday] [2014 11 25 :tuesday] [2014 11 26 :wednesday]
       [2014 11 28 :friday] [2014 12 1 :monday] [2014 12 2 :tuesday]
       [2014 12 3 :wednesday] [2014 12 4 :thursday] [2014 12 5 :friday]
       [2014 12 8 :monday] [2014 12 9 :tuesday] [2014 12 10 :wednesday]
       [2014 12 11 :thursday] [2014 12 12 :friday] [2014 12 15 :monday]
       [2014 12 16 :tuesday] [2014 12 17 :wednesday] [2014 12 18 :thursday]
       [2014 12 19 :friday] [2014 12 22 :monday] [2014 12 23 :tuesday]
       [2014 12 24 :wednesday] [2014 12 26 :friday] [2014 12 29 :monday]
       [2014 12 30 :tuesday] [2014 12 31 :wednesday]]))
