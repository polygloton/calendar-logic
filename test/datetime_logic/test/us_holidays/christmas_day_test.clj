(ns datetime-logic.test.us-holidays.christmas-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [christmas-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest christmas-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (christmas-day year month day)))
    => [[1971 12 25] [1972 12 25] [1973 12 25] [1974 12 25] [1975 12 25] [1976 12 25] [1977 12 25] [1978 12 25]
        [1979 12 25] [1980 12 25] [1981 12 25] [1982 12 25] [1983 12 25] [1984 12 25] [1985 12 25] [1986 12 25]
        [1987 12 25] [1988 12 25] [1989 12 25] [1990 12 25] [1991 12 25] [1992 12 25] [1993 12 25] [1994 12 25]
        [1995 12 25] [1996 12 25] [1997 12 25] [1998 12 25] [1999 12 25] [2000 12 25] [2001 12 25] [2002 12 25]
        [2003 12 25] [2004 12 25] [2005 12 25] [2006 12 25] [2007 12 25] [2008 12 25] [2009 12 25] [2010 12 25]
        [2011 12 25] [2012 12 25] [2013 12 25] [2014 12 25] [2015 12 25] [2016 12 25] [2017 12 25] [2018 12 25]
        [2019 12 25] [2020 12 25]])

  (eg
    (run* [year]
      (christmas-day year 1 1))
    => []))