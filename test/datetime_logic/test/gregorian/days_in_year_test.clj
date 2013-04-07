(ns datetime-logic.test.gregorian.days-in-year-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [days-in-year]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest days-in-year-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2013 1 1 q))
    => [1])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2013 1 10 q))
    => [10])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2013 1 31 q))
    => [31])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2013 2 1 q))
    => [32])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2013 10 10 q))
    => [283])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2012 11 30 q))
    => [335])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2012 12 1 q))
    => [336])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2013 12 31 q))
    => [365])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (days-in-year 2012 12 31 q))
    => [366])

  (eg
    (set
      (run* [days]
        (fd/in days (fd/interval 1 365))
        (fresh [month]
          (fd/in month (fd/interval 1 12))
          (days-in-year 2013 month 1 days))))
    => #{1 32 60 91 121 152 182 213 244 274 305 335})

  (eg
    (set
      (run* [days]
        (fd/in days (fd/interval 1 366))
        (fresh [month]
          (fd/in month (fd/interval 1 12))
          (days-in-year 2012 month 1 days))))
    => #{1 32 61 92 122 153 183 214 245 275 306 336})

  (eg
    (set
      (run* [year]
        (fd/in year (fd/interval 1990 2020))
        (days-in-year year 12 31 366)))
    => #{1992 1996 2000 2004 2008 2012 2016 2020})

  (eg
    (set
      (run* [month day]
        (fresh [days]
          (fd/in days (fd/interval 300 310))
          (fd/in month (fd/interval 1 12))
          (fd/in day (fd/interval 1 31))
          (days-in-year 2013 month day days))))
    => #{[10 27] [10 28] [10 29] [10 30] [10 31] [11 1] [11 2] [11 3] [11 4] [11 5] [11 6]})
)