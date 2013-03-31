(ns datetime-logic.test.gregorian.month-aftero-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [month-aftero]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest month-aftero-test

  (eg
    (run* [q]
      (month-aftero :december q))
    => [])

  (eg
    (run* [q]
      (month-aftero :november q))
    => [:december])

  (eg
    (run* [q]
      (month-aftero :october q))
    => [:november])

  (eg
    (run* [q]
      (month-aftero :september q))
    => [:october])

  (eg
    (run* [q]
      (month-aftero :august q))
    => [:september])

  (eg
    (run* [q]
      (month-aftero :july q))
    => [:august])

  (eg
    (run* [q]
      (month-aftero :june q))
    => [:july])

  (eg
    (run* [q]
      (month-aftero :may q))
    => [:june])

  (eg
    (run* [q]
      (month-aftero :april q))
    => [:may])

  (eg
    (run* [q]
      (month-aftero :march q))
    => [:april])

  (eg
    (run* [q]
      (month-aftero :february q))
    => [:march])

  (eg
    (run* [q]
      (month-aftero :january q))
    => [:february])

  (eg (set
        (run* [q]
          (fresh [m]
            (conde
              [(== m :april)]
              [(== m :september)])
            (month-aftero m q))))
      => #{:may :october}))