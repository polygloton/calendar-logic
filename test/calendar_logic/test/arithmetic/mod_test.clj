(ns calendar-logic.test.arithmetic.mod-test
  (:refer-clojure :exclude [== mod])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.arithmetic :only [mod]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest mod-test
  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (mod 9 4 q))
    => [1])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (mod 10 4 q))
    => [2])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (mod 11 4 q))
    => [3])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (mod 12 4 q))
    => [0])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 12))
      (mod 11 q 3))
    => [4 8])

  (eg
    (run 6 [q]
      (fd/in q (fd/interval 0 12))
      (mod q 2 1))
    => [1 3 5 7 9 11])

  (eg
   (sort
    (run 99 [x y]
         (fd/in x y (fd/interval 1 Integer/MAX_VALUE))
         (fd/< y x)
         (mod x y 3)))
   => [[7 4] [8 5] [9 6] [10 7] [11 4] [11 8] [12 9] [13 5] [13 10] [14 11] [15 4]
       [15 6] [15 12] [16 13] [17 7] [17 14] [18 5] [18 15] [19 4] [19 8] [19 16]
       [20 17] [21 6] [21 9] [21 18] [22 19] [23 4] [23 5] [23 10] [23 20] [24 7]
       [24 21] [25 11] [25 22] [26 23] [27 4] [27 6] [27 8] [27 12] [27 24] [28 5]
       [28 25] [29 13] [29 26] [30 9] [30 27] [31 4] [31 7] [31 14] [31 28] [32 29]
       [33 5] [33 6] [33 10] [33 15] [33 30] [34 31] [35 4] [35 8] [35 16] [35 32]
       [36 11] [36 33] [37 17] [37 34] [38 5] [38 7] [38 35] [39 4] [39 6] [39 9]
       [39 12] [39 18] [39 36] [40 37] [41 19] [41 38] [42 13] [42 39] [43 4] [43 5]
       [43 8] [43 10] [43 20] [43 40] [44 41] [45 6] [45 7] [45 14] [45 21] [45 42]
       [46 43] [47 4] [47 11] [47 22] [48 5] [48 9] [48 15] [51 4]])

  (eg
    (take-last 10
      (run* [x y]
        (fd/in x y (fd/interval 1 100))
        (fd/< y x)
        (mod x y 10)))
    => [[98 22] [100 18] [96 86] [98 44] [97 87] [100 30] [100 45] [98 88] [99 89] [100 90]]))
