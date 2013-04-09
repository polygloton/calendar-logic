(ns datetime-logic.test.us-holidays.fathers-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [fathers-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest fathers-day-test
  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (fathers-day year month day)))
    => [[1971 6 20] [1972 6 18] [1973 6 17] [1974 6 16] [1975 6 15] [1976 6 20] [1977 6 19] [1978 6 18] [1979 6 17] [1980 6 15] [1981 6 21] [1982 6 20] [1983 6 19] [1984 6 17] [1985 6 16] [1986 6 15] [1987 6 21] [1988 6 19] [1989 6 18] [1990 6 17] [1991 6 16] [1992 6 21] [1993 6 20] [1994 6 19] [1995 6 18] [1996 6 16] [1997 6 15] [1998 6 21] [1999 6 20] [2000 6 18] [2001 6 17] [2002 6 16] [2003 6 15] [2004 6 20] [2005 6 19] [2006 6 18] [2007 6 17] [2008 6 15] [2009 6 21] [2010 6 20] [2011 6 19] [2012 6 17] [2013 6 16] [2014 6 15] [2015 6 21] [2016 6 19] [2017 6 18] [2018 6 17] [2019 6 16] [2020 6 21]])

  (eg
    (run* [year]
      (fathers-day year 1 1))
    => []))