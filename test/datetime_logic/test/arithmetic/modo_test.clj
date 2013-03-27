(ns datetime-logic.test.arithmetic.modo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    datetime-logic.arithmetic
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest modo-test
  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (modo 9 4 q))
    => [1])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (modo 10 4 q))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (modo 11 4 q))
    => [3])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (modo 12 4 q))
    => [0])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (modo 11 q 3))
    => [4 8])

  (eg
    (run 6 [q]
      (fd/in q (fd/interval 0 12))
      (modo q 2 1))
    => [1 3 5 7 9 11])

  (eg
    (run 99 [x y]
      (fd/in x y (fd/interval 1 Integer/MAX_VALUE))
      (fd/< y x)
      (modo x y 3))
    => [[7 4] [8 5] [9 6] [10 7] [11 4] [11 8] [13 5] [12 9] [15 4] [13 10] [15 6] [14 11] [15 12] [18 5] [17 7]
        [19 4] [16 13] [17 14] [19 8] [18 15] [21 6] [19 16] [23 4] [21 9] [20 17] [23 5] [21 18] [24 7] [23 10]
        [22 19] [23 20] [27 4] [25 11] [24 21] [28 5] [27 6] [25 22] [27 8] [27 12] [26 23] [31 4] [27 24] [29 13]
        [28 25] [30 9] [31 7] [29 26] [33 5] [33 6] [31 14] [30 27] [35 4] [31 28] [33 10] [33 15] [32 29] [35 8]
        [33 30] [38 5] [35 16] [34 31] [36 11] [39 4] [38 7] [35 32] [39 6] [37 17] [36 33] [39 9] [37 34] [39 12]
        [39 18] [38 35] [43 4] [39 36] [43 5] [41 19] [40 37] [42 13] [43 8] [43 10] [41 38] [45 6] [45 7] [43 20]
        [42 39] [47 4] [43 40] [45 14] [48 5] [45 21] [44 41] [47 11] [45 42] [48 9] [51 4] [47 22] [46 43] [48 15]])

  (eg
    (take-last 10
      (run* [x y]
        (fd/in x y (fd/interval 1 100))
        (fd/< y x)
        (modo x y 10)))
    => [[98 22] [100 18] [96 86] [98 44] [97 87] [100 30] [100 45] [98 88] [99 89] [100 90]]))