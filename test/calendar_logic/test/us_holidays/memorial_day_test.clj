(ns calendar-logic.test.us-holidays.memorial-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [memorial-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest memorial-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (memorial-day year month day)))
    => [[1971 5 31] [1972 5 29] [1973 5 28] [1974 5 27] [1975 5 26] [1976 5 31] [1977 5 30] [1978 5 29] [1979 5 28]
        [1980 5 26] [1981 5 25] [1982 5 31] [1983 5 30] [1984 5 28] [1985 5 27] [1986 5 26] [1987 5 25] [1988 5 30]
        [1989 5 29] [1990 5 28] [1991 5 27] [1992 5 25] [1993 5 31] [1994 5 30] [1995 5 29] [1996 5 27] [1997 5 26]
        [1998 5 25] [1999 5 31] [2000 5 29] [2001 5 28] [2002 5 27] [2003 5 26] [2004 5 31] [2005 5 30] [2006 5 29]
        [2007 5 28] [2008 5 26] [2009 5 25] [2010 5 31] [2011 5 30] [2012 5 28] [2013 5 27] [2014 5 26] [2015 5 25]
        [2016 5 30] [2017 5 29] [2018 5 28] [2019 5 27] [2020 5 25]])

  (eg
    (run* [year]
      (fd/in year (fd/interval 1 Integer/MAX_VALUE))
      (fresh [day]
        (fd/in day (fd/interval 1 31))
        (memorial-day year 6 day)))
    => [])
  )
