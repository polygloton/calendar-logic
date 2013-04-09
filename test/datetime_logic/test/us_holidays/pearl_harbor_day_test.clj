(ns datetime-logic.test.us-holidays.pearl-harbor-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [pearl-harbor-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest pearl-harbor-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (pearl-harbor-day year month day)))
    => [[1971 12 7] [1972 12 7] [1973 12 7] [1974 12 7] [1975 12 7] [1976 12 7] [1977 12 7] [1978 12 7] [1979 12 7] [1980 12 7] [1981 12 7] [1982 12 7] [1983 12 7] [1984 12 7] [1985 12 7] [1986 12 7] [1987 12 7] [1988 12 7] [1989 12 7] [1990 12 7] [1991 12 7] [1992 12 7] [1993 12 7] [1994 12 7] [1995 12 7] [1996 12 7] [1997 12 7] [1998 12 7] [1999 12 7] [2000 12 7] [2001 12 7] [2002 12 7] [2003 12 7] [2004 12 7] [2005 12 7] [2006 12 7] [2007 12 7] [2008 12 7] [2009 12 7] [2010 12 7] [2011 12 7] [2012 12 7] [2013 12 7] [2014 12 7] [2015 12 7] [2016 12 7] [2017 12 7] [2018 12 7] [2019 12 7] [2020 12 7]])

  (eg
    (run* [year]
      (pearl-harbor-day year 11 12))
    => []))