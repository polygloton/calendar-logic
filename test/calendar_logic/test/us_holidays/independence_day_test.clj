(ns calendar-logic.test.us-holidays.independence-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [independence-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest independence-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (independence-day year month day)))
    => [[1971 7 4] [1972 7 4] [1973 7 4] [1974 7 4] [1975 7 4] [1976 7 4] [1977 7 4] [1978 7 4] [1979 7 4] [1980 7 4]
        [1981 7 4] [1982 7 4] [1983 7 4] [1984 7 4] [1985 7 4] [1986 7 4] [1987 7 4] [1988 7 4] [1989 7 4] [1990 7 4]
        [1991 7 4] [1992 7 4] [1993 7 4] [1994 7 4] [1995 7 4] [1996 7 4] [1997 7 4] [1998 7 4] [1999 7 4] [2000 7 4]
        [2001 7 4] [2002 7 4] [2003 7 4] [2004 7 4] [2005 7 4] [2006 7 4] [2007 7 4] [2008 7 4] [2009 7 4] [2010 7 4]
        [2011 7 4] [2012 7 4] [2013 7 4] [2014 7 4] [2015 7 4] [2016 7 4] [2017 7 4] [2018 7 4] [2019 7 4] [2020 7 4]])

  (eg
    (run* [year]
      (independence-day year 8 4))
    => []))
