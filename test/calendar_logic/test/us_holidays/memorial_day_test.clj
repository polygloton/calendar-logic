(ns calendar-logic.test.us-holidays.memorial-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-memorial-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/memorial-day year month day)))
   [[1971 5 31] [1972 5 29] [1973 5 28] [1974 5 27] [1975 5 26] [1976 5 31]
    [1977 5 30] [1978 5 29] [1979 5 28] [1980 5 26] [1981 5 25] [1982 5 31]
    [1983 5 30] [1984 5 28] [1985 5 27] [1986 5 26] [1987 5 25] [1988 5 30]
    [1989 5 29] [1990 5 28] [1991 5 27] [1992 5 25] [1993 5 31] [1994 5 30]
    [1995 5 29] [1996 5 27] [1997 5 26] [1998 5 25] [1999 5 31] [2000 5 29]
    [2001 5 28] [2002 5 27] [2003 5 26] [2004 5 31] [2005 5 30] [2006 5 29]
    [2007 5 28] [2008 5 26] [2009 5 25] [2010 5 31] [2011 5 30] [2012 5 28]
    [2013 5 27] [2014 5 26] [2015 5 25] [2016 5 30] [2017 5 29] [2018 5 28]
    [2019 5 27] [2020 5 25]])

  (eg
   (run* [year month day]
         (== month 6)
         (hol/memorial-day year month day))
   []))

(deftest test-not-memorial-day
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 5)
          (hol/not-memorial-day year month day)))
   [[2014 5 1] [2014 5 2] [2014 5 3] [2014 5 4] [2014 5 5] [2014 5 6]
    [2014 5 7] [2014 5 8] [2014 5 9] [2014 5 10] [2014 5 11] [2014 5 12]
    [2014 5 13] [2014 5 14] [2014 5 15] [2014 5 16] [2014 5 17] [2014 5 18]
    [2014 5 19] [2014 5 20] [2014 5 21] [2014 5 22] [2014 5 23] [2014 5 24]
    [2014 5 25] [2014 5 27] [2014 5 28] [2014 5 29] [2014 5 30] [2014 5 31]]))
