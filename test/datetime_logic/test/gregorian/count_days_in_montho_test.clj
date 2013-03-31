(ns datetime-logic.test.gregorian.count-days-in-montho-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [count-days-in-montho]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest count-days-in-montho-test

  (eg
    (run* [q]
      (count-days-in-montho :february [2000 2] q))
    => [29])

  (eg
    (run* [q]
      (count-days-in-montho :february [1999 2] q))
    => [28])

  (eg
    (run* [q]
      (count-days-in-montho :january [2000 2] q))
    => [31])

  (eg
    (run* [q]
      (count-days-in-montho :march [2000 2] q))
    => [0])

  (eg
    (set
      (run* [q]
        (count-days-in-montho q [2013 12] 31)))
    => #{:january :march :may :july :august :october :december})

  (eg
    (set
      (run* [q]
        (fresh [x y]
          (== q [x y])
          (fd/in x (fd/interval 1999 2000))
          (fd/in y (fd/interval 1 12))
          (count-days-in-montho :april q 30))))
    => #{[1999 4] [1999 5] [1999 6] [1999 7] [1999 8] [1999 9] [1999 10] [1999 11] [1999 12]
         [2000 4] [2000 5] [2000 6] [2000 7] [2000 8] [2000 9] [2000 10] [2000 11] [2000 12]}))