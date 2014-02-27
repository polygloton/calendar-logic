(ns calendar-logic.test.us-holidays.valentines-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-valentines-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/valentines-day year month day)))
   => [[1971 2 14] [1972 2 14] [1973 2 14] [1974 2 14] [1975 2 14] [1976 2 14]
       [1977 2 14] [1978 2 14] [1979 2 14] [1980 2 14] [1981 2 14] [1982 2 14]
       [1983 2 14] [1984 2 14] [1985 2 14] [1986 2 14] [1987 2 14] [1988 2 14]
       [1989 2 14] [1990 2 14] [1991 2 14] [1992 2 14] [1993 2 14] [1994 2 14]
       [1995 2 14] [1996 2 14] [1997 2 14] [1998 2 14] [1999 2 14] [2000 2 14]
       [2001 2 14] [2002 2 14] [2003 2 14] [2004 2 14] [2005 2 14] [2006 2 14]
       [2007 2 14] [2008 2 14] [2009 2 14] [2010 2 14] [2011 2 14] [2012 2 14]
       [2013 2 14] [2014 2 14] [2015 2 14] [2016 2 14] [2017 2 14] [2018 2 14]
       [2019 2 14] [2020 2 14]])

  (eg
   (run* [year]
         (hol/valentines-day year 3 2))
   => []))

(deftest test-not-valentines-day
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 2)
          (hol/not-valentines-day year month day)))
   => [[2014 2 1] [2014 2 2] [2014 2 3] [2014 2 4] [2014 2 5] [2014 2 6]
       [2014 2 7] [2014 2 8] [2014 2 9] [2014 2 10] [2014 2 11] [2014 2 12]
       [2014 2 13] [2014 2 15] [2014 2 16] [2014 2 17] [2014 2 18] [2014 2 19]
       [2014 2 20] [2014 2 21] [2014 2 22] [2014 2 23] [2014 2 24] [2014 2 25]
       [2014 2 26] [2014 2 27] [2014 2 28]]))
