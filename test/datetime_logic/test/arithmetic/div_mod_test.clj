(ns datetime-logic.test.arithmetic.div-mod-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.arithmetic :only [div-mod]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest div-mod-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-mod 5 2 1 q))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-mod 5 2 2 q))
    => [])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-mod 6 q q q))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-mod q 2 1 2))
    => [5])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-mod 7 3 q 2))
    => [1])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-mod 7 q 1 2))
    => [3]))