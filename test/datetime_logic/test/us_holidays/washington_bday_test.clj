(ns datetime-logic.test.us-holidays.washington-bday-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [washington-bday]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest washington-bday-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (washington-bday year month day)))
    => [[1971 2 15] [1972 2 21] [1973 2 19] [1974 2 18] [1975 2 17] [1976 2 16] [1977 2 21] [1978 2 20] [1979 2 19]
        [1980 2 18] [1981 2 16] [1982 2 15] [1983 2 21] [1984 2 20] [1985 2 18] [1986 2 17] [1987 2 16] [1988 2 15]
        [1989 2 20] [1990 2 19] [1991 2 18] [1992 2 17] [1993 2 15] [1994 2 21] [1995 2 20] [1996 2 19] [1997 2 17]
        [1998 2 16] [1999 2 15] [2000 2 21] [2001 2 19] [2002 2 18] [2003 2 17] [2004 2 16] [2005 2 21] [2006 2 20]
        [2007 2 19] [2008 2 18] [2009 2 16] [2010 2 15] [2011 2 21] [2012 2 20] [2013 2 18] [2014 2 17] [2015 2 16]
        [2016 2 15] [2017 2 20] [2018 2 19] [2019 2 18] [2020 2 17]])

  (eg
    (run* [year]
      (fd/in year (fd/interval 1 Integer/MAX_VALUE))
      (fresh [day]
        (fd/in day (fd/interval 1 30))
        (washington-bday year 3 day)))
    => []))