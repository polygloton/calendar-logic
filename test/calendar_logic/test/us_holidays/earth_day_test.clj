(ns calendar-logic.test.us-holidays.earth-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-earth-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/earth-day year month day)))
   => [[1971 4 22] [1972 4 22] [1973 4 22] [1974 4 22] [1975 4 22] [1976 4 22]
       [1977 4 22] [1978 4 22] [1979 4 22] [1980 4 22] [1981 4 22] [1982 4 22]
       [1983 4 22] [1984 4 22] [1985 4 22] [1986 4 22] [1987 4 22] [1988 4 22]
       [1989 4 22] [1990 4 22] [1991 4 22] [1992 4 22] [1993 4 22] [1994 4 22]
       [1995 4 22] [1996 4 22] [1997 4 22] [1998 4 22] [1999 4 22] [2000 4 22]
       [2001 4 22] [2002 4 22] [2003 4 22] [2004 4 22] [2005 4 22] [2006 4 22]
       [2007 4 22] [2008 4 22] [2009 4 22] [2010 4 22] [2011 4 22] [2012 4 22]
       [2013 4 22] [2014 4 22] [2015 4 22] [2016 4 22] [2017 4 22] [2018 4 22]
       [2019 4 22] [2020 4 22]])

  (eg
   (run* [year]
         (hol/earth-day year 5 22))
   => []))

(deftest test-not-earth-day
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 4)
          (hol/not-earth-day year month day)))
   => [[2014 4 1] [2014 4 2] [2014 4 3] [2014 4 4] [2014 4 5] [2014 4 6]
       [2014 4 7] [2014 4 8] [2014 4 9] [2014 4 10] [2014 4 11] [2014 4 12]
       [2014 4 13] [2014 4 14] [2014 4 15] [2014 4 16] [2014 4 17] [2014 4 18]
       [2014 4 19] [2014 4 20] [2014 4 21] [2014 4 23] [2014 4 24] [2014 4 25]
       [2014 4 26] [2014 4 27] [2014 4 28] [2014 4 29] [2014 4 30]]))
