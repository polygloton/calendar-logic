(ns datetime-logic.test.gregorian.fixed-from-gregorian-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [fixed-from-gregorian]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest fixed-from-gregorian-test

  (eg (run* [days]
        (fd/in days (fd/interval 0 Integer/MAX_VALUE))
        (fixed-from-gregorian 1945 11 12 days))
    => [710347])

  (eg (run* [year]
        (fd/in year (fd/interval 0 Integer/MAX_VALUE))
        (fixed-from-gregorian year 11 12 710347))
    => [1945])

  (eg (run* [month]
        (fd/in month (fd/interval 0 Integer/MAX_VALUE))
        (fixed-from-gregorian 1945 month 12 710347))
    => [11])

  (eg (run* [day]
        (fd/in day (fd/interval 0 Integer/MAX_VALUE))
        (fixed-from-gregorian 1945 11 day 710347))
    => [12])

  (eg
    (set
      (run* [year days]
        (fd/in year (fd/interval 2000 2009))
        (fd/in days (fd/interval 700000 800000))
        (fixed-from-gregorian year 10 31 days)))
    => #{[2000 730424] [2001 730789] [2002 731154] [2003 731519] [2004 731885]
         [2005 732250] [2006 732615] [2007 732980] [2008 733346] [2009 733711]}))
