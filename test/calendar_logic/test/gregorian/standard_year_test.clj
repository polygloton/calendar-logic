(ns calendar-logic.test.gregorian.standard-year-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.gregorian :only [standard-year]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest standard-year-test

  (eg
    (run* [q]
      (standard-year 2000) (== q true))
    => [])

  (eg
    (run* [q]
      (standard-year 2400) (== q true))
    => [])

  (eg
    (run* [q]
      (standard-year 2004) (== q true))
    => [])

  (eg
    (run* [q]
      (standard-year 1800) (== q true))
    => [true])

  (eg
    (run* [q]
      (standard-year 1900) (== q true))
    => [true])

  (eg
    (run* [q]
      (standard-year 1999) (== q true))
    => [true])

  (eg
    (set
      (run* [q]
        (fd/in q (fd/interval 1990 2010))
        (standard-year q)))
    => #{1990 1991 1993 1994 1995 1997 1998 1999 2001 2002 2003 2005 2006 2007 2009 2010}))
