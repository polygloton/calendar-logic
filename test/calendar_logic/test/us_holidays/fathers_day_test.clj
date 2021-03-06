(ns calendar-logic.test.us-holidays.fathers-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-fathers-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/fathers-day year month day)))
   [[1971 6 20] [1972 6 18] [1973 6 17] [1974 6 16] [1975 6 15] [1976 6 20]
    [1977 6 19] [1978 6 18] [1979 6 17] [1980 6 15] [1981 6 21] [1982 6 20]
    [1983 6 19] [1984 6 17] [1985 6 16] [1986 6 15] [1987 6 21] [1988 6 19]
    [1989 6 18] [1990 6 17] [1991 6 16] [1992 6 21] [1993 6 20] [1994 6 19]
    [1995 6 18] [1996 6 16] [1997 6 15] [1998 6 21] [1999 6 20] [2000 6 18]
    [2001 6 17] [2002 6 16] [2003 6 15] [2004 6 20] [2005 6 19] [2006 6 18]
    [2007 6 17] [2008 6 15] [2009 6 21] [2010 6 20] [2011 6 19] [2012 6 17]
    [2013 6 16] [2014 6 15] [2015 6 21] [2016 6 19] [2017 6 18] [2018 6 17]
    [2019 6 16] [2020 6 21]])

  (eg
   (run* [year]
         (hol/fathers-day year 1 1))
   []))

(deftest test-not-fathers-day
  (eg (sort
       (run* [year month day]
             (== year 2014)
             (== month 6)
             (hol/not-fathers-day year month day)))
      [[2014 6 1] [2014 6 2] [2014 6 3] [2014 6 4] [2014 6 5] [2014 6 6]
       [2014 6 7] [2014 6 8] [2014 6 9] [2014 6 10] [2014 6 11] [2014 6 12]
       [2014 6 13] [2014 6 14] [2014 6 16] [2014 6 17] [2014 6 18] [2014 6 19]
       [2014 6 20] [2014 6 21] [2014 6 22] [2014 6 23] [2014 6 24] [2014 6 25]
       [2014 6 26] [2014 6 27] [2014 6 28] [2014 6 29] [2014 6 30]]))
