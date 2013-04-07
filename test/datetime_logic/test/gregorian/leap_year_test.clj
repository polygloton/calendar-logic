(ns datetime-logic.test.gregorian.leap-year-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [leap-year]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest leap-year-test

  (eg
    (run* [q]
      (leap-year 2000) (== q true))
    => [true])

  (eg
    (run* [q]
      (leap-year 2400) (== q true))
    => [true])

  (eg
    (run* [q]
      (leap-year 2004) (== q true))
    => [true])

  (eg
    (run* [q]
      (leap-year 1800) (== q true))
    => [])

  (eg
    (run* [q]
      (leap-year 1900) (== q true))
    => [])

  (eg
    (run* [q]
      (leap-year 1999) (== q true))
    => [])

  (eg
    (set
      (run* [q]
        (fd/in q (fd/interval 1990 2010))
        (leap-year q)))
    => #{1992 1996 2000 2004 2008}))