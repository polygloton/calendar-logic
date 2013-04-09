(ns datetime-logic.test.us-holidays.halloween-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [halloween]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest halloween-test
  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (halloween year month day)))
    => [[1971 10 31] [1972 10 31] [1973 10 31] [1974 10 31] [1975 10 31] [1976 10 31] [1977 10 31] [1978 10 31] [1979 10 31] [1980 10 31] [1981 10 31] [1982 10 31] [1983 10 31] [1984 10 31] [1985 10 31] [1986 10 31] [1987 10 31] [1988 10 31] [1989 10 31] [1990 10 31] [1991 10 31] [1992 10 31] [1993 10 31] [1994 10 31] [1995 10 31] [1996 10 31] [1997 10 31] [1998 10 31] [1999 10 31] [2000 10 31] [2001 10 31] [2002 10 31] [2003 10 31] [2004 10 31] [2005 10 31] [2006 10 31] [2007 10 31] [2008 10 31] [2009 10 31] [2010 10 31] [2011 10 31] [2012 10 31] [2013 10 31] [2014 10 31] [2015 10 31] [2016 10 31] [2017 10 31] [2018 10 31] [2019 10 31] [2020 10 31]])

  (eg
    (run* [year]
      (halloween year 1 1))
    => []))