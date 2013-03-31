(ns datetime-logic.test.gregorian.month-numbero-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [month-numbero]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest month-numbero-test
  (eg
    (run* [q]
      (month-numbero :foo q))
    => [])

  (eg
    (run* [q]
      (month-numbero :january q))
    => [1])

  (eg
    (run* [q]
      (month-numbero :february q))
    => [2])

  (eg
    (run* [q]
      (month-numbero :march q))
    => [3])

  (eg
    (run* [q]
      (month-numbero :april q))
    => [4])

  (eg
    (run* [q]
      (month-numbero :may q))
    => [5])

  (eg
    (run* [q]
      (month-numbero :june q))
    => [6])

  (eg
    (run* [q]
      (month-numbero :july q))
    => [7])

  (eg
    (run* [q]
      (month-numbero :august q))
    => [8])

  (eg
    (run* [q]
      (month-numbero :september q))
    => [9])

  (eg
    (run* [q]
      (month-numbero :october q))
    => [10])

  (eg
    (run* [q]
      (month-numbero :november q))
    => [11])

  (eg
    (run* [q]
      (month-numbero :december q))
    => [12])

  (eg
    (run* [q]
      (month-numbero q 10))
    => [:october])

  (eg
    (set
      (run* [q]
        (fresh [num]
          (conde
            [(== num 3)]
            [(== num 6)])
          (month-numbero q num))))
    => #{:march :june}))