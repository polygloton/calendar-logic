(ns datetime-logic.test.us-holidays.groundhog-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [groundhog-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest groundhog-day-test
  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (groundhog-day year month day)))
    => [[1971 2 2] [1972 2 2] [1973 2 2] [1974 2 2] [1975 2 2] [1976 2 2] [1977 2 2] [1978 2 2] [1979 2 2] [1980 2 2]
        [1981 2 2] [1982 2 2] [1983 2 2] [1984 2 2] [1985 2 2] [1986 2 2] [1987 2 2] [1988 2 2] [1989 2 2] [1990 2 2]
        [1991 2 2] [1992 2 2] [1993 2 2] [1994 2 2] [1995 2 2] [1996 2 2] [1997 2 2] [1998 2 2] [1999 2 2] [2000 2 2]
        [2001 2 2] [2002 2 2] [2003 2 2] [2004 2 2] [2005 2 2] [2006 2 2] [2007 2 2] [2008 2 2] [2009 2 2] [2010 2 2]
        [2011 2 2] [2012 2 2] [2013 2 2] [2014 2 2] [2015 2 2] [2016 2 2] [2017 2 2] [2018 2 2] [2019 2 2] [2020 2 2]])

  (eg
    (run* [year]
      (groundhog-day year 3 2))
    => []))