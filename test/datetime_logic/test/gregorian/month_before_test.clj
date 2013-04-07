(ns datetime-logic.test.gregorian.month-before-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [month-before]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest month-before-test

  (eg
    (run* [q]
      (month-before :january q))
    => [])

  (eg
    (run* [q]
      (month-before :february q))
    => [:january])

  (eg
    (run* [q]
      (month-before :march q))
    => [:february])

  (eg
    (run* [q]
      (month-before :april q))
    => [:march])

  (eg
    (run* [q]
      (month-before :may q))
    => [:april])

  (eg
    (run* [q]
      (month-before :june q))
    => [:may])

  (eg
    (run* [q]
      (month-before :july q))
    => [:june])

  (eg
    (run* [q]
      (month-before :august q))
    => [:july])

  (eg
    (run* [q]
      (month-before :september q))
    => [:august])

  (eg
    (run* [q]
      (month-before :october q))
    => [:september])

  (eg
    (run* [q]
      (month-before :november q))
    => [:october])

  (eg
    (run* [q]
      (month-before :december q))
    => [:november])

  (eg
    (run* [q]
      (month-before q :february))
    => [:march])

  (eg (set
        (run* [q]
          (fresh [m]
            (conde
              [(== m :april)]
              [(== m :september)])
            (month-before m q))))
    => #{:march :august}))