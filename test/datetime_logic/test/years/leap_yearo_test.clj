(ns datetime-logic.test.years.leap-yearo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.years :only [leap-yearo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest leap-yearo-test

  (eg
    (run* [q]
      (leap-yearo 2000) (== q true))
    => [true])

  (eg
    (run* [q]
      (leap-yearo 2400) (== q true))
    => [true])

  (eg
    (run* [q]
      (leap-yearo 2004) (== q true))
    => [true])

  (eg
    (run* [q]
      (leap-yearo 1800) (== q true))
    => [])

  (eg
    (run* [q]
      (leap-yearo 1900) (== q true))
    => [])

  (eg
    (run* [q]
      (leap-yearo 1999) (== q true))
    => [])

  (eg
    (set
      (run* [q]
        (fd/in q (fd/interval 1990 2010))
        (leap-yearo q)))
    => #{1992 1996 2000 2004 2008}))