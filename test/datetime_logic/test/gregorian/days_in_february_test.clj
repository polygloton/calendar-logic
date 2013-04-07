(ns datetime-logic.test.gregorian.days-in-february-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [days-in-february]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest days-in-february-test

  (eg
    (run* [q]
      (days-in-february 2000 q))
    => [29])

  (eg
    (run* [q]
      (days-in-february 2400 q))
    => [29])

  (eg
    (run* [q]
      (days-in-february 2004 q))
    => [29])

  (eg
    (run* [q]
      (days-in-february 1800 q))
    => [28])

  (eg
    (run* [q]
      (days-in-february 1900 q))
    => [28])

  (eg
    (run* [q]
      (days-in-february 1999 q))
    => [28])

  (eg
    (set
      (run* [q]
        (fresh [year]
          (conde
            [(== year 1999)]
            [(== year 2000)])
          (days-in-february year q))))
    => #{28 29}))