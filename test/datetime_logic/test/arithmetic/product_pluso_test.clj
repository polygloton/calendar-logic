(ns datetime-logic.test.arithmetic.product-pluso-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.arithmetic :only [product-pluso]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest product-pluso-test
  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (product-pluso 2 2 1 q))
    => [5])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (product-pluso 2 q 1 5))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (product-pluso q q 1 5))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 100))
      (product-pluso q q 2 6))
    => [2])

  (eg
    (run* [q]
      (fresh [x]
        (fd/in x q (fd/interval 0 100))
        (conde
          [(== x 2)]
          [(== x 3)]
          [(== x 4)])
        (product-pluso x 10 q 40)))
    => [20 10 0])

  (eg
      (run* [x y]
        (fd/in x y (fd/interval 1 38))
        (product-pluso x y 2 40))
      => [[1 38] [2 19] [19 2] [38 1]]))