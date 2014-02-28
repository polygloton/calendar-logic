(ns calendar-logic.test.us-holidays.patriot-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-patriot-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/patriot-day year month day)))
   [[1971 9 11] [1972 9 11] [1973 9 11] [1974 9 11] [1975 9 11] [1976 9 11]
    [1977 9 11] [1978 9 11] [1979 9 11] [1980 9 11] [1981 9 11] [1982 9 11]
    [1983 9 11] [1984 9 11] [1985 9 11] [1986 9 11] [1987 9 11] [1988 9 11]
    [1989 9 11] [1990 9 11] [1991 9 11] [1992 9 11] [1993 9 11] [1994 9 11]
    [1995 9 11] [1996 9 11] [1997 9 11] [1998 9 11] [1999 9 11] [2000 9 11]
    [2001 9 11] [2002 9 11] [2003 9 11] [2004 9 11] [2005 9 11] [2006 9 11]
    [2007 9 11] [2008 9 11] [2009 9 11] [2010 9 11] [2011 9 11] [2012 9 11]
    [2013 9 11] [2014 9 11] [2015 9 11] [2016 9 11] [2017 9 11] [2018 9 11]
    [2019 9 11] [2020 9 11]])

  (eg
   (run* [year]
         (hol/patriot-day year 9 10))
   []))

(deftest test-not-patriot-day
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 9)
          (hol/not-patriot-day year month day)))
   [[2014 9 1] [2014 9 2] [2014 9 3] [2014 9 4] [2014 9 5] [2014 9 6]
    [2014 9 7] [2014 9 8] [2014 9 9] [2014 9 10] [2014 9 12] [2014 9 13]
    [2014 9 14] [2014 9 15] [2014 9 16] [2014 9 17] [2014 9 18] [2014 9 19]
    [2014 9 20] [2014 9 21] [2014 9 22] [2014 9 23] [2014 9 24] [2014 9 25]
    [2014 9 26] [2014 9 27] [2014 9 28] [2014 9 29] [2014 9 30]]))
