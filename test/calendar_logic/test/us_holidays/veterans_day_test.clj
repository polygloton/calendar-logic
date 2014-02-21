(ns calendar-logic.test.us-holidays.veterans-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [veterans-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest veterans-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (veterans-day year month day)))
    => [[1971 11 11] [1972 11 11] [1973 11 11] [1974 11 11] [1975 11 11] [1976 11 11] [1977 11 11] [1978 11 11]
        [1979 11 11] [1980 11 11] [1981 11 11] [1982 11 11] [1983 11 11] [1984 11 11] [1985 11 11] [1986 11 11]
        [1987 11 11] [1988 11 11] [1989 11 11] [1990 11 11] [1991 11 11] [1992 11 11] [1993 11 11] [1994 11 11]
        [1995 11 11] [1996 11 11] [1997 11 11] [1998 11 11] [1999 11 11] [2000 11 11] [2001 11 11] [2002 11 11]
        [2003 11 11] [2004 11 11] [2005 11 11] [2006 11 11] [2007 11 11] [2008 11 11] [2009 11 11] [2010 11 11]
        [2011 11 11] [2012 11 11] [2013 11 11] [2014 11 11] [2015 11 11] [2016 11 11] [2017 11 11] [2018 11 11]
        [2019 11 11] [2020 11 11]])

  (eg
    (run* [year]
      (veterans-day year 11 12))
    => []))
