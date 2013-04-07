(ns datetime-logic.test.us-holidays.columbus-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [columbus-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest columbus-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (columbus-day year month day)))
    => [[1971 10 11] [1972 10 9]  [1973 10 8]  [1974 10 14] [1975 10 13] [1976 10 11] [1977 10 10] [1978 10 9]
        [1979 10 8]  [1980 10 13] [1981 10 12] [1982 10 11] [1983 10 10] [1984 10 8]  [1985 10 14] [1986 10 13]
        [1987 10 12] [1988 10 10] [1989 10 9]  [1990 10 8]  [1991 10 14] [1992 10 12] [1993 10 11] [1994 10 10]
        [1995 10 9]  [1996 10 14] [1997 10 13] [1998 10 12] [1999 10 11] [2000 10 9]  [2001 10 8]  [2002 10 14]
        [2003 10 13] [2004 10 11] [2005 10 10] [2006 10 9]  [2007 10 8]  [2008 10 13] [2009 10 12] [2010 10 11]
        [2011 10 10] [2012 10 8]  [2013 10 14] [2014 10 13] [2015 10 12] [2016 10 10] [2017 10 9]  [2018 10 8]
        [2019 10 14] [2020 10 12]])

  (eg
    (run* [year]
      (columbus-day year 11 11))
    => []))