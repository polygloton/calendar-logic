(ns datetime-logic.test.arithmetic.div-modo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.arithmetic :only [div-modo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest div-modo-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-modo 5 2 1 q))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-modo 5 2 2 q))
    => [])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-modo 6 q q q))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-modo q 2 1 2))
    => [5])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-modo 7 3 q 2))
    => [1])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-modo 7 q 1 2))
    => [3]))