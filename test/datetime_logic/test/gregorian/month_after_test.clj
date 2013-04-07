(ns datetime-logic.test.gregorian.month-after-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [month-after]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest month-after-test

  (eg
    (run* [q]
      (month-after :december q))
    => [])

  (eg
    (run* [q]
      (month-after :november q))
    => [:december])

  (eg
    (run* [q]
      (month-after :october q))
    => [:november])

  (eg
    (run* [q]
      (month-after :september q))
    => [:october])

  (eg
    (run* [q]
      (month-after :august q))
    => [:september])

  (eg
    (run* [q]
      (month-after :july q))
    => [:august])

  (eg
    (run* [q]
      (month-after :june q))
    => [:july])

  (eg
    (run* [q]
      (month-after :may q))
    => [:june])

  (eg
    (run* [q]
      (month-after :april q))
    => [:may])

  (eg
    (run* [q]
      (month-after :march q))
    => [:april])

  (eg
    (run* [q]
      (month-after :february q))
    => [:march])

  (eg
    (run* [q]
      (month-after :january q))
    => [:february])

  (eg (set
        (run* [q]
          (fresh [m]
            (conde
              [(== m :april)]
              [(== m :september)])
            (month-after m q))))
      => #{:may :october}))