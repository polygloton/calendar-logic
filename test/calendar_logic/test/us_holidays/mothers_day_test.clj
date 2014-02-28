(ns calendar-logic.test.us-holidays.mothers-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-mothers-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/mothers-day year month day)))
   [[1971 5 9] [1972 5 14] [1973 5 13] [1974 5 12] [1975 5 11] [1976 5 9]
    [1977 5 8] [1978 5 14] [1979 5 13] [1980 5 11] [1981 5 10] [1982 5 9]
    [1983 5 8] [1984 5 13] [1985 5 12] [1986 5 11] [1987 5 10] [1988 5 8]
    [1989 5 14] [1990 5 13] [1991 5 12] [1992 5 10] [1993 5 9] [1994 5 8]
    [1995 5 14] [1996 5 12] [1997 5 11] [1998 5 10] [1999 5 9] [2000 5 14]
    [2001 5 13] [2002 5 12] [2003 5 11] [2004 5 9] [2005 5 8] [2006 5 14]
    [2007 5 13] [2008 5 11] [2009 5 10] [2010 5 9] [2011 5 8] [2012 5 13]
    [2013 5 12] [2014 5 11] [2015 5 10] [2016 5 8] [2017 5 14] [2018 5 13]
    [2019 5 12] [2020 5 10]])

  (eg
   (run* [year]
         (hol/mothers-day year 1 1))
   []))

(deftest test-not-mothersday
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 5)
          (hol/not-mothers-day year month day)))
   [[2014 5 1] [2014 5 2] [2014 5 3] [2014 5 4] [2014 5 5] [2014 5 6]
    [2014 5 7] [2014 5 8] [2014 5 9] [2014 5 10] [2014 5 12] [2014 5 13]
    [2014 5 14] [2014 5 15] [2014 5 16] [2014 5 17] [2014 5 18] [2014 5 19]
    [2014 5 20] [2014 5 21] [2014 5 22] [2014 5 23] [2014 5 24] [2014 5 25]
    [2014 5 26] [2014 5 27] [2014 5 28] [2014 5 29] [2014 5 30] [2014 5 31]]))
