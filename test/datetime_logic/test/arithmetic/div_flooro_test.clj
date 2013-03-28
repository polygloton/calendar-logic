(ns datetime-logic.test.arithmetic.div-flooro-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.arithmetic :only [div-flooro]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest div-flooro-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-flooro 100 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-flooro 101 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-flooro 102 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-flooro 103 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-flooro 104 4 q))
    => [26])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-flooro 104 q 26))
    => [4])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 1000))
      (div-flooro q 4 26))
    => [104 105 106 107]))