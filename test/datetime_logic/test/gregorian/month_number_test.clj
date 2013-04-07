(ns datetime-logic.test.gregorian.month-number-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [month-number]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest month-number-test
  (eg
    (run* [q]
      (month-number :foo q))
    => [])

  (eg
    (run* [q]
      (month-number :january q))
    => [1])

  (eg
    (run* [q]
      (month-number :february q))
    => [2])

  (eg
    (run* [q]
      (month-number :march q))
    => [3])

  (eg
    (run* [q]
      (month-number :april q))
    => [4])

  (eg
    (run* [q]
      (month-number :may q))
    => [5])

  (eg
    (run* [q]
      (month-number :june q))
    => [6])

  (eg
    (run* [q]
      (month-number :july q))
    => [7])

  (eg
    (run* [q]
      (month-number :august q))
    => [8])

  (eg
    (run* [q]
      (month-number :september q))
    => [9])

  (eg
    (run* [q]
      (month-number :october q))
    => [10])

  (eg
    (run* [q]
      (month-number :november q))
    => [11])

  (eg
    (run* [q]
      (month-number :december q))
    => [12])

  (eg
    (run* [q]
      (month-number q 10))
    => [:october])

  (eg
    (set
      (run* [q]
        (fresh [num]
          (conde
            [(== num 3)]
            [(== num 6)])
          (month-number q num))))
    => #{:march :june}))