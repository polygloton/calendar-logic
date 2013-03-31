(ns datetime-logic.test.gregorian.days-in-februaryo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [days-in-februaryo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest days-in-februaryo-test

  (eg
    (run* [q]
      (days-in-februaryo 2000 q))
    => [29])

  (eg
    (run* [q]
      (days-in-februaryo 2400 q))
    => [29])

  (eg
    (run* [q]
      (days-in-februaryo 2004 q))
    => [29])

  (eg
    (run* [q]
      (days-in-februaryo 1800 q))
    => [28])

  (eg
    (run* [q]
      (days-in-februaryo 1900 q))
    => [28])

  (eg
    (run* [q]
      (days-in-februaryo 1999 q))
    => [28])

  (eg
    (set
      (run* [q]
        (fresh [year]
          (conde
            [(== year 1999)]
            [(== year 2000)])
          (days-in-februaryo year q))))
    => #{28 29}))