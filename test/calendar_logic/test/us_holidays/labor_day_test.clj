(ns calendar-logic.test.us-holidays.labor-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [labor-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest labor-day-test

  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (labor-day year month day)))
    => [[1971 9 6] [1972 9 4] [1973 9 3] [1974 9 2] [1975 9 1] [1976 9 6] [1977 9 5] [1978 9 4] [1979 9 3] [1980 9 1]
        [1981 9 7] [1982 9 6] [1983 9 5] [1984 9 3] [1985 9 2] [1986 9 1] [1987 9 7] [1988 9 5] [1989 9 4] [1990 9 3]
        [1991 9 2] [1992 9 7] [1993 9 6] [1994 9 5] [1995 9 4] [1996 9 2] [1997 9 1] [1998 9 7] [1999 9 6] [2000 9 4]
        [2001 9 3] [2002 9 2] [2003 9 1] [2004 9 6] [2005 9 5] [2006 9 4] [2007 9 3] [2008 9 1] [2009 9 7] [2010 9 6]
        [2011 9 5] [2012 9 3] [2013 9 2] [2014 9 1] [2015 9 7] [2016 9 5] [2017 9 4] [2018 9 3] [2019 9 2] [2020 9 7]])

  (eg
    (run* [year]
      (fd/in year (fd/interval 1 Integer/MAX_VALUE))
      (fresh [day]
        (fd/in day (fd/interval 1 31))
        (labor-day year 10 day)))
    => []))
