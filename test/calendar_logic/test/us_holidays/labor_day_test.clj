(ns calendar-logic.test.us-holidays.labor-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-labor-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/labor-day year month day)))
   [[1971 9 6] [1972 9 4] [1973 9 3] [1974 9 2] [1975 9 1] [1976 9 6] [1977 9 5]
    [1978 9 4] [1979 9 3] [1980 9 1] [1981 9 7] [1982 9 6] [1983 9 5] [1984 9 3]
    [1985 9 2] [1986 9 1] [1987 9 7] [1988 9 5] [1989 9 4] [1990 9 3] [1991 9 2]
    [1992 9 7] [1993 9 6] [1994 9 5] [1995 9 4] [1996 9 2] [1997 9 1] [1998 9 7]
    [1999 9 6] [2000 9 4] [2001 9 3] [2002 9 2] [2003 9 1] [2004 9 6] [2005 9 5]
    [2006 9 4] [2007 9 3] [2008 9 1] [2009 9 7] [2010 9 6] [2011 9 5] [2012 9 3]
    [2013 9 2] [2014 9 1] [2015 9 7] [2016 9 5] [2017 9 4] [2018 9 3] [2019 9 2]
    [2020 9 7]])

  (eg
   (run* [year]
         (fresh [day]
                (fd/in day (fd/interval 1 31))
                (hol/labor-day year 10 day)))
   []))

(deftest test-not-labor-day
  (eg
   (sort
    (run* [year month day]
          (== year 2014)
          (== month 9)
          (hol/not-labor-day year month day)))
   [[2014 9 2] [2014 9 3] [2014 9 4] [2014 9 5] [2014 9 6] [2014 9 7] [2014 9 8]
    [2014 9 9] [2014 9 10] [2014 9 11] [2014 9 12] [2014 9 13] [2014 9 14] [2014 9 15]
    [2014 9 16] [2014 9 17] [2014 9 18] [2014 9 19] [2014 9 20] [2014 9 21] [2014 9 22]
    [2014 9 23] [2014 9 24] [2014 9 25] [2014 9 26] [2014 9 27] [2014 9 28] [2014 9 29]
    [2014 9 30]]))
