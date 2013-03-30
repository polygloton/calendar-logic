(ns datetime-logic.test.gregorian.years.standard-yearo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian.years :only [standard-yearo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest standard-yearo-test

  (eg
    (run* [q]
      (standard-yearo 2000) (== q true))
    => [])

  (eg
    (run* [q]
      (standard-yearo 2400) (== q true))
    => [])

  (eg
    (run* [q]
      (standard-yearo 2004) (== q true))
    => [])

  (eg
    (run* [q]
      (standard-yearo 1800) (== q true))
    => [true])

  (eg
    (run* [q]
      (standard-yearo 1900) (== q true))
    => [true])

  (eg
    (run* [q]
      (standard-yearo 1999) (== q true))
    => [true])

  (eg
    (set
      (run* [q]
        (fd/in q (fd/interval 1990 2010))
        (standard-yearo q)))
    => #{1990 1991 1993 1994 1995 1997 1998 1999 2001 2002 2003 2005 2006 2007 2009 2010}))