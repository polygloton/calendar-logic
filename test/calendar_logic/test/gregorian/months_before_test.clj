(ns calendar-logic.test.gregorian.months-before-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.gregorian :only [months-before]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest months-before-test

  (eg
    (run* [q]
      (months-before :january q))
    => [])

  (eg
    (run* [q]
      (months-before :february q))
    => [:january])

  (eg
    (set
      (run* [q]
        (months-before :march q)))
    => #{:january :february})

  (eg
    (set
      (run* [q]
        (months-before :april q)))
    => #{:january :february :march})

  (eg
    (set
      (run* [q]
        (months-before :may q)))
    => #{:january :february :march :april})

  (eg
    (set
      (run* [q]
        (months-before :june q)))
    => #{:january :february :march :april :may})

  (eg
    (set
      (run* [q]
        (months-before :july q)))
    => #{:january :february :march :april :may :june})

  (eg
    (set
      (run* [q]
        (months-before :august q)))
    => #{:january :february :march :april :may :june :july})

  (eg
    (set
      (run* [q]
        (months-before :september q)))
    => #{:january :february :march :april :may :june :july :august})

  (eg
    (set
      (run* [q]
        (months-before :october q)))
    => #{:january :february :march :april :may :june :july :august :september})

  (eg
    (set
      (run* [q]
        (months-before :november q)))
    => #{:january :february :march :april :may :june :july :august :september :october})

  (eg
    (set
      (run* [q]
        (months-before :december q)))
    => #{:january :february :march :april :may :june :july :august :september :october :november})

  (eg
    (set
      (run* [q]
        (months-before :october q) (months-before :may q)))
    => #{:january :february :march :april}))
