(ns datetime-logic.test.gregorian.months.months-beforeo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian.months :only [months-beforeo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest months-beforeo-test

  (eg
    (run* [q]
      (months-beforeo :january q))
    => [])

  (eg
    (run* [q]
      (months-beforeo :february q))
    => [:january])

  (eg
    (set
      (run* [q]
        (months-beforeo :march q)))
    => #{:january :february})

  (eg
    (set
      (run* [q]
        (months-beforeo :april q)))
    => #{:january :february :march})

  (eg
    (set
      (run* [q]
        (months-beforeo :may q)))
    => #{:january :february :march :april})

  (eg
    (set
      (run* [q]
        (months-beforeo :june q)))
    => #{:january :february :march :april :may})

  (eg
    (set
      (run* [q]
        (months-beforeo :july q)))
    => #{:january :february :march :april :may :june})

  (eg
    (set
      (run* [q]
        (months-beforeo :august q)))
    => #{:january :february :march :april :may :june :july})

  (eg
    (set
      (run* [q]
        (months-beforeo :september q)))
    => #{:january :february :march :april :may :june :july :august})

  (eg
    (set
      (run* [q]
        (months-beforeo :october q)))
    => #{:january :february :march :april :may :june :july :august :september})

  (eg
    (set
      (run* [q]
        (months-beforeo :november q)))
    => #{:january :february :march :april :may :june :july :august :september :october})

  (eg
    (set
      (run* [q]
        (months-beforeo :december q)))
    => #{:january :february :march :april :may :june :july :august :september :october :november})

  (eg
    (set
      (run* [q]
        (months-beforeo :october q) (months-beforeo :may q)))
    => #{:january :february :march :april}))