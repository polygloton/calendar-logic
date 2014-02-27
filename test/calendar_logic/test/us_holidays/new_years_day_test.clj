(ns calendar-logic.test.us-holidays.new-years-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [clojure.core.logic :exclude [is]])
  (:require
   [clojure.core.logic.fd :as fd]
   [calendar-logic.us-holidays :as hol]))

(deftest test-new-years-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/new-years-day year month day)))
   => [[1971 1 1] [1972 1 1] [1973 1 1] [1974 1 1] [1975 1 1] [1976 1 1] [1977 1 1]
       [1978 1 1] [1979 1 1] [1980 1 1] [1981 1 1] [1982 1 1] [1983 1 1] [1984 1 1]
       [1985 1 1] [1986 1 1] [1987 1 1] [1988 1 1] [1989 1 1] [1990 1 1] [1991 1 1]
       [1992 1 1] [1993 1 1] [1994 1 1] [1995 1 1] [1996 1 1] [1997 1 1] [1998 1 1]
       [1999 1 1] [2000 1 1] [2001 1 1] [2002 1 1] [2003 1 1] [2004 1 1] [2005 1 1]
       [2006 1 1] [2007 1 1] [2008 1 1] [2009 1 1] [2010 1 1] [2011 1 1] [2012 1 1]
       [2013 1 1] [2014 1 1] [2015 1 1] [2016 1 1] [2017 1 1] [2018 1 1] [2019 1 1]
       [2020 1 1]])

  (eg
   (run* [year]
         (hol/new-years-day year 1 2))
   => []))

(deftest test-not-new-years-day
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 1)
          (hol/not-new-years-day year month day)))
   => [[2014 1 2] [2014 1 3] [2014 1 4] [2014 1 5] [2014 1 6] [2014 1 7]
       [2014 1 8] [2014 1 9] [2014 1 10] [2014 1 11] [2014 1 12] [2014 1 13]
       [2014 1 14] [2014 1 15] [2014 1 16] [2014 1 17] [2014 1 18] [2014 1 19]
       [2014 1 20] [2014 1 21] [2014 1 22] [2014 1 23] [2014 1 24] [2014 1 25]
       [2014 1 26] [2014 1 27] [2014 1 28] [2014 1 29] [2014 1 30] [2014 1 31]]))
