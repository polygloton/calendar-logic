(ns calendar-logic.test.gregorian.days-in-month-name-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.gregorian :only [days-in-month-name]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest days-in-month-name-test

  (eg
    (run* [q]
      (days-in-month-name 2013 :january q))
    => [31])

  (eg
    (run* [q]
      (days-in-month-name 2013 q 31))
    => [:january :march :may :july :august :october :december])

  (eg
    (run* [q]
      (days-in-month-name 2013 q 30))
    => [:april :june :september :november])

  (eg
    (run* [q]
      (days-in-month-name 2013 q 28))
    => [:february])

  (eg
    (run* [q]
      (days-in-month-name 2013 q 29))
    => [])

  (eg
    (run* [q]
      (days-in-month-name 2000 q 29))
    => [:february])

  (eg
    (set
      (run* [year month]
        (fd/in year (fd/interval 1990 2010))
        (days-in-month-name year month 28)))
  => #{[1990 :february] [1991 :february] [1993 :february] [1994 :february] [1995 :february] [1997 :february]
       [1998 :february] [1999 :february] [2001 :february] [2002 :february] [2003 :february] [2005 :february]
       [2006 :february] [2007 :february] [2009 :february] [2010 :february]}))
