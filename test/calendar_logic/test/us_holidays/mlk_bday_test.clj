(ns calendar-logic.test.us-holidays.mlk-bday-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [mlk-bday]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest mlk-bday-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (mlk-bday year month day)))
    => [[1971 1 18] [1972 1 17] [1973 1 15] [1974 1 21] [1975 1 20] [1976 1 19] [1977 1 17] [1978 1 16] [1979 1 15]
        [1980 1 21] [1981 1 19] [1982 1 18] [1983 1 17] [1984 1 16] [1985 1 21] [1986 1 20] [1987 1 19] [1988 1 18]
        [1989 1 16] [1990 1 15] [1991 1 21] [1992 1 20] [1993 1 18] [1994 1 17] [1995 1 16] [1996 1 15] [1997 1 20]
        [1998 1 19] [1999 1 18] [2000 1 17] [2001 1 15] [2002 1 21] [2003 1 20] [2004 1 19] [2005 1 17] [2006 1 16]
        [2007 1 15] [2008 1 21] [2009 1 19] [2010 1 18] [2011 1 17] [2012 1 16] [2013 1 21] [2014 1 20] [2015 1 19]
        [2016 1 18] [2017 1 16] [2018 1 15] [2019 1 21] [2020 1 20]])

  (eg
    (run* [year]
      (fd/in year (fd/interval 1 Integer/MAX_VALUE))
      (fresh [day]
        (fd/in day (fd/interval 1 31))
        (mlk-bday year 2 day)))
    => []))
