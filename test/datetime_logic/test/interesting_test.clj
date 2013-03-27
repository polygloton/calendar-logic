(ns datetime-logic.test.interesting-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    datetime-logic.core
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]
    [clj-time.core :as t]))

(deftest beforeo-and-aftero-test
  (let [present (t/date-time 2013 3 21 3 31 0 439)
        year-ago (t/minus present (t/years 1))
        year-from (t/plus present (t/years 1))]

    (eg
      (run 20 [year month day hour minute second millisec]
        (fresh [out]
          (fd/distinct [year month day hour minute second millisec])
          (aftero [year month day hour minute second millisec] year-ago out)
          (beforeo [year month day hour minute second millisec] year-from out)))
      => [[2013 1 2 0 3 4 5] [2014 1 2 0 3 4 5] [2013 2 1 0 3 4 5]  [2013 1 3 0 2 4 5] [2014 3 1 0 2 4 5]
          [2013 1 2 3 0 4 5] [2014 2 1 0 3 4 5] [2014 3 21 0 1 2 4] [2013 1 2 0 4 3 5] [2013 1 2 0 3 4 6]
          [2013 1 2 0 3 5 4] [2013 1 2 0 3 4 7] [2014 1 3 0 2 4 5]  [2013 3 1 0 2 4 5] [2013 2 3 0 1 4 5]
          [2014 3 2 0 1 4 5] [2013 1 4 0 2 3 5] [2014 1 2 3 0 4 5]  [2014 2 3 0 1 4 5] [2013 2 1 3 0 4 5]])

    (eg
      (run 20 [year month day hour minute second millisec]
        (fresh [in]
          (fd/distinct [year month day hour minute second millisec])
          (aftero in year-ago [year month day hour minute second millisec])
          (beforeo in year-from [year month day hour minute second millisec])))
      => [[2013 1 2 0 3 4 5] [2014 1 2 0 3 4 5] [2013 2 1 0 3 4 5]  [2013 1 3 0 2 4 5] [2014 3 1 0 2 4 5]
          [2013 1 2 3 0 4 5] [2014 2 1 0 3 4 5] [2014 3 21 0 1 2 4] [2013 1 2 0 4 3 5] [2013 1 2 0 3 4 6]
          [2013 1 2 0 3 5 4] [2013 1 2 0 3 4 7] [2014 1 3 0 2 4 5]  [2013 3 1 0 2 4 5] [2013 2 3 0 1 4 5]
          [2014 3 2 0 1 4 5] [2013 1 4 0 2 3 5] [2014 1 2 3 0 4 5]  [2014 2 3 0 1 4 5] [2013 2 1 3 0 4 5]])
  )
)

(deftest next-15th-and-1st-test
  (let [present (t/date-time 2013 3 21 3 31 0 439)
        next-month (t/plus present (t/months 4))]

    (eg
      (into #{}
        (run* [year month day]
          (fresh [hour minute second millisec out]
            (fd/distinct [year month day])
            (== 0 hour) (== 0 minute) (== 0 second) (== 0 millisec)
            (conde
              [(== day 15)]
              [(== day 1)])
            (aftero [year month day hour minute second millisec] present out)
            (beforeo [year month day hour minute second millisec] next-month out))))
      => #{[2013 4 1] [2013 4 15] [2013 5 1] [2013 5 15] [2013 6 1] [2013 6 15] [2013 7 1] [2013 7 15]})

  )
)