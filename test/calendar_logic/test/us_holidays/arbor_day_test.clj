(ns calendar-logic.test.us-holidays.arbor-day-test
  (:refer-clojure :exclude [==])
  (:use clojure.test
        calendar-logic.test.test-helper
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest test-arbor-day
  (eg
   (sort
    (run* [year month day]
          (fd/in year (fd/interval 1971 2020))
          (hol/arbor-day year month day)))
   [[1971 4 30] [1972 4 28] [1973 4 27] [1974 4 26] [1975 4 25]
    [1976 4 30] [1977 4 29] [1978 4 28] [1979 4 27] [1980 4 25] [1981 4 24]
    [1982 4 30] [1983 4 29] [1984 4 27] [1985 4 26] [1986 4 25] [1987 4 24]
    [1988 4 29] [1989 4 28] [1990 4 27] [1991 4 26] [1992 4 24] [1993 4 30]
    [1994 4 29] [1995 4 28] [1996 4 26] [1997 4 25] [1998 4 24] [1999 4 30]
    [2000 4 28] [2001 4 27] [2002 4 26] [2003 4 25] [2004 4 30] [2005 4 29]
    [2006 4 28] [2007 4 27] [2008 4 25] [2009 4 24] [2010 4 30] [2011 4 29]
    [2012 4 27] [2013 4 26] [2014 4 25] [2015 4 24] [2016 4 29] [2017 4 28]
    [2018 4 27] [2019 4 26] [2020 4 24]])

  (eg
   (run* [year]
         (fd/in year (fd/interval 1 Integer/MAX_VALUE))
         (fresh [day]
                (fd/in day (fd/interval 1 31))
                (hol/arbor-day year 6 day)))
   []))

(deftest test-not-arbor-day
  (eg (sort
       (run* [year month day]
             (== year 2014)
             (== month 4)
             (hol/not-arbor-day year month day)))
      [[2014 4 1] [2014 4 2] [2014 4 3] [2014 4 4] [2014 4 5] [2014 4 6]
       [2014 4 7] [2014 4 8] [2014 4 9] [2014 4 10] [2014 4 11] [2014 4 12]
       [2014 4 13] [2014 4 14] [2014 4 15] [2014 4 16] [2014 4 17] [2014 4 18]
       [2014 4 19] [2014 4 20] [2014 4 21] [2014 4 22] [2014 4 23] [2014 4 24]
       [2014 4 26] [2014 4 27] [2014 4 28] [2014 4 29] [2014 4 30]]))
