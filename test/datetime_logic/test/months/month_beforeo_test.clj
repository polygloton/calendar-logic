(ns datetime-logic.test.months.month-beforeo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.months :only [month-beforeo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest month-beforeo-test

  (eg
    (run* [q]
      (month-beforeo :january q))
    => [])

  (eg
    (run* [q]
      (month-beforeo :february q))
    => [:january])

  (eg
    (run* [q]
      (month-beforeo :march q))
    => [:february])

  (eg
    (run* [q]
      (month-beforeo :april q))
    => [:march])

  (eg
    (run* [q]
      (month-beforeo :may q))
    => [:april])

  (eg
    (run* [q]
      (month-beforeo :june q))
    => [:may])

  (eg
    (run* [q]
      (month-beforeo :july q))
    => [:june])

  (eg
    (run* [q]
      (month-beforeo :august q))
    => [:july])

  (eg
    (run* [q]
      (month-beforeo :september q))
    => [:august])

  (eg
    (run* [q]
      (month-beforeo :october q))
    => [:september])

  (eg
    (run* [q]
      (month-beforeo :november q))
    => [:october])

  (eg
    (run* [q]
      (month-beforeo :december q))
    => [:november])

  (eg
    (run* [q]
      (month-beforeo q :february))
    => [:march])

  (eg (set
        (run* [q]
          (fresh [m]
            (conde
              [(== m :april)]
              [(== m :september)])
            (month-beforeo m q))))
    => #{:march :august}))