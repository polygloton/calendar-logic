(ns datetime-logic.test.gregorian.days-in-montho-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [days-in-montho]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest days-in-montho-test

  (eg
    (run* [q]
      (days-in-montho 2013 1 q))
    => [31])

  (eg
    (run* [q]
      (days-in-montho 2013 q 31))
    => [1 3 5 7 8 10 12])

  (eg
    (run* [q]
      (days-in-montho 2013 q 30))
    => [4 6 9 11])

  (eg
    (run* [q]
      (days-in-montho 2013 q 28))
    => [2])

  (eg
    (run* [q]
      (days-in-montho 2013 q 29))
    => [])

  (eg
    (run* [q]
      (days-in-montho 2000 q 29))
    => [2]))