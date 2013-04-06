(ns datetime-logic.test.gregorian.day-of-the-weeko-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [day-of-the-weeko]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest day-of-the-weeko-test

  (eg
    (run* [day-name]
      (day-of-the-weeko 2013 4 6 day-name))
    => [:saturday])

  (eg
    (set
      (run* [year]
        (fd/in year (fd/interval 2000 2020))
        (day-of-the-weeko year 4 6 :saturday)))
    => #{2002 2013 2019})

  (eg
    (set
      (run* [day]
        (fd/in day (fd/interval 1 30))
        (day-of-the-weeko 2013 4 day :saturday)))
    => #{6 13 20 27})

  (eg
    (set
      (run* [month]
        (fd/in month (fd/interval 1 12))
        (day-of-the-weeko 2013 month 13 :friday)))
    => #{9 12}))