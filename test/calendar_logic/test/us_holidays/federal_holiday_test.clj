(ns calendar-logic.test.us-holidays.federal-holiday-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [federal-holiday]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest federal-holiday-test

  (eg
    (sort
      (run* [year month day name]
        (== year 2013)
        (federal-holiday year month day name)))
    => [[2013 1 1 :new-years-day]  [2013 1 21 :mlk-bday]        [2013 2 18 :washington-bday]
        [2013 5 27 :memorial-day]  [2013 7 4 :independence-day] [2013 9 2 :labor-day]
        [2013 10 14 :columbus-day] [2013 11 11 :veterans-day]   [2013 11 28 :thanksgiving-day]
        [2013 12 25 :christmas-day]]))
