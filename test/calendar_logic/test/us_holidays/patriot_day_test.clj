(ns calendar-logic.test.us-holidays.patriot-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [patriot-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest patriot-day-test
  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (patriot-day year month day)))
    => [[1971 9 11] [1972 9 11] [1973 9 11] [1974 9 11] [1975 9 11] [1976 9 11] [1977 9 11] [1978 9 11] [1979 9 11] [1980 9 11] [1981 9 11] [1982 9 11] [1983 9 11] [1984 9 11] [1985 9 11] [1986 9 11] [1987 9 11] [1988 9 11] [1989 9 11] [1990 9 11] [1991 9 11] [1992 9 11] [1993 9 11] [1994 9 11] [1995 9 11] [1996 9 11] [1997 9 11] [1998 9 11] [1999 9 11] [2000 9 11] [2001 9 11] [2002 9 11] [2003 9 11] [2004 9 11] [2005 9 11] [2006 9 11] [2007 9 11] [2008 9 11] [2009 9 11] [2010 9 11] [2011 9 11] [2012 9 11] [2013 9 11] [2014 9 11] [2015 9 11] [2016 9 11] [2017 9 11] [2018 9 11] [2019 9 11] [2020 9 11]])

  (eg
    (run* [year]
      (patriot-day year 9 10))
    => []))
