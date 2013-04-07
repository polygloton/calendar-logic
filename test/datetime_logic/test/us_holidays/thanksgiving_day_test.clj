(ns datetime-logic.test.us-holidays.thanksgiving-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [thanksgiving-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest thanksgiving-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (thanksgiving-day year month day)))
    => [[1971 11 25] [1972 11 23] [1973 11 22] [1974 11 28] [1975 11 27] [1976 11 25] [1977 11 24] [1978 11 23]
        [1979 11 22] [1980 11 27] [1981 11 26] [1982 11 25] [1983 11 24] [1984 11 22] [1985 11 28] [1986 11 27]
        [1987 11 26] [1988 11 24] [1989 11 23] [1990 11 22] [1991 11 28] [1992 11 26] [1993 11 25] [1994 11 24]
        [1995 11 23] [1996 11 28] [1997 11 27] [1998 11 26] [1999 11 25] [2000 11 23] [2001 11 22] [2002 11 28]
        [2003 11 27] [2004 11 25] [2005 11 24] [2006 11 23] [2007 11 22] [2008 11 27] [2009 11 26] [2010 11 25]
        [2011 11 24] [2012 11 22] [2013 11 28] [2014 11 27] [2015 11 26] [2016 11 24] [2017 11 23] [2018 11 22]
        [2019 11 28] [2020 11 26]])

  (eg
    (set
      (run* [year]
        (fd/in year (fd/interval 1971 2020))
        (thanksgiving-day year 11 28)))
    => #{1974 1985 1991 2002 1996 2013 2019})

  (eg
    (run* [year]
      (fd/in year (fd/interval 1 Integer/MAX_VALUE))
      (fresh [day]
        (fd/in day (fd/interval 1 31))
        (thanksgiving-day year 12 day)))
    => []))