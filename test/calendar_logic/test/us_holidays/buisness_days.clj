(ns calendar-logic.test.us-holidays.buisness-days
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic :exclude [is]]
        [clojure.test])
  (:require [calendar-logic.us-holidays :as hol]
            [calendar-logic.gregorian :as cal]
            [clojure.core.logic.fd :as fd]))

(deftest test-business-days-in-feb-2014
  (is (= [[2014 2 3 :monday] [2014 2 4 :tuesday] [2014 2 5 :wednesday] [2014 2 6 :thursday]
          [2014 2 7 :friday] [2014 2 10 :monday] [2014 2 11 :tuesday] [2014 2 12 :wednesday]
          [2014 2 13 :thursday] [2014 2 14 :friday] [2014 2 18 :tuesday] [2014 2 19 :wednesday]
          [2014 2 20 :thursday] [2014 2 21 :friday] [2014 2 24 :monday] [2014 2 25 :tuesday]
          [2014 2 26 :wednesday] [2014 2 27 :thursday] [2014 2 28 :friday]]

         (sort
          (run* [year month day dow]
                (== year 2014)
                (== month 2)
                (cal/weekday dow)
                (cal/day-in-month year month day)
                (cal/day-of-the-week year month day dow)
                (fresh [h-day h-name]
                       (fd/in h-day (fd/interval 1 29))
                       (hol/federal-holiday year month h-day h-name)
                       (!= [year month day] [year month h-day])))))))
