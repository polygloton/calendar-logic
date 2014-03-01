(ns calendar-logic.test.us-holidays.pearl-harbor-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-pearl-harbor-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/pearl-harbor-day year month day)))
   [[1971 12 7] [1972 12 7] [1973 12 7] [1974 12 7] [1975 12 7] [1976 12 7]
    [1977 12 7] [1978 12 7] [1979 12 7] [1980 12 7] [1981 12 7] [1982 12 7]
    [1983 12 7] [1984 12 7] [1985 12 7] [1986 12 7] [1987 12 7] [1988 12 7]
    [1989 12 7] [1990 12 7] [1991 12 7] [1992 12 7] [1993 12 7] [1994 12 7]
    [1995 12 7] [1996 12 7] [1997 12 7] [1998 12 7] [1999 12 7] [2000 12 7]
    [2001 12 7] [2002 12 7] [2003 12 7] [2004 12 7] [2005 12 7] [2006 12 7]
    [2007 12 7] [2008 12 7] [2009 12 7] [2010 12 7] [2011 12 7] [2012 12 7]
    [2013 12 7] [2014 12 7] [2015 12 7] [2016 12 7] [2017 12 7] [2018 12 7]
    [2019 12 7] [2020 12 7]])

  (eg
   (run* [year]
         (hol/pearl-harbor-day year 11 12))
   []))

(deftest test-not-pearl-harbor-day
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 12)
          (hol/not-pearl-harbor-day year month day)))
   [[2014 12 1] [2014 12 2] [2014 12 3] [2014 12 4] [2014 12 5] [2014 12 6]
    [2014 12 8] [2014 12 9] [2014 12 10] [2014 12 11] [2014 12 12] [2014 12 13]
    [2014 12 14] [2014 12 15] [2014 12 16] [2014 12 17] [2014 12 18] [2014 12 19]
    [2014 12 20] [2014 12 21] [2014 12 22] [2014 12 23] [2014 12 24] [2014 12 25]
    [2014 12 26] [2014 12 27] [2014 12 28] [2014 12 29] [2014 12 30] [2014 12 31]]))
