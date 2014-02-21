(ns calendar-logic.test.arithmetic.div-floor-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.arithmetic :only [div-floor]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest div-floor-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-floor 100 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-floor 101 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-floor 102 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-floor 103 4 q))
    => [25])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-floor 104 4 q))
    => [26])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (div-floor 104 q 26))
    => [4])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 1000))
      (div-floor q 4 26))
    => [104 105 106 107]))
