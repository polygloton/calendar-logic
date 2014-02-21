(ns calendar-logic.test.us-holidays.flag-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [flag-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest flag-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (flag-day year month day)))
    => [[1971 6 14] [1972 6 14] [1973 6 14] [1974 6 14] [1975 6 14] [1976 6 14] [1977 6 14] [1978 6 14] [1979 6 14] [1980 6 14] [1981 6 14] [1982 6 14] [1983 6 14] [1984 6 14] [1985 6 14] [1986 6 14] [1987 6 14] [1988 6 14] [1989 6 14] [1990 6 14] [1991 6 14] [1992 6 14] [1993 6 14] [1994 6 14] [1995 6 14] [1996 6 14] [1997 6 14] [1998 6 14] [1999 6 14] [2000 6 14] [2001 6 14] [2002 6 14] [2003 6 14] [2004 6 14] [2005 6 14] [2006 6 14] [2007 6 14] [2008 6 14] [2009 6 14] [2010 6 14] [2011 6 14] [2012 6 14] [2013 6 14] [2014 6 14] [2015 6 14] [2016 6 14] [2017 6 14] [2018 6 14] [2019 6 14] [2020 6 14]])

  (eg
    (run* [year]
      (flag-day year 7 14))
    => []))
