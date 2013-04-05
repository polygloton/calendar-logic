(ns datetime-logic.test.gregorian.count-days-in-yearo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [count-days-in-yearo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest count-days-in-yearo-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-days-in-yearo 2013 1 1 q))
    => [1])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-days-in-yearo 2013 1 10 q))
    => [10])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-days-in-yearo 2013 1 31 q))
    => [31])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-days-in-yearo 2013 2 1 q))
    => [32])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-days-in-yearo 2013 10 10 q))
    => [283])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-days-in-yearo 2013 12 31 q))
    => [365])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-days-in-yearo 2012 12 31 q))
    => [366])

  (eg
    (into #{}
      (run* [days]
        (fd/in days (fd/interval 1 365))
        (fresh [month]
          (fd/in month (fd/interval 1 12))
          (count-days-in-yearo 2013 month 1 days))))
    => #{1 32 60 91 121 152 182 213 244 274 305 335})

  (eg
    (into #{}
      (run* [year]
        (fd/in year (fd/interval 1990 2020))
        (count-days-in-yearo year 12 31 366)))
    => #{1992 1996 2000 2004 2008 2012 2016 2020})

  (eg
    (into #{}
      (run* [month day]
        (fresh [days]
          (fd/in days (fd/interval 300 310))
          (fd/in month (fd/interval 1 12))
          (fd/in day (fd/interval 1 31))
          (count-days-in-yearo 2013 month day days))))
    => #{[10 27] [10 28] [10 29] [10 30] [10 31] [11 1] [11 2] [11 3] [11 4] [11 5] [11 6]})
)