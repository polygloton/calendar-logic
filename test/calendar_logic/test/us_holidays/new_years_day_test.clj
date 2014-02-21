(ns calendar-logic.test.us-holidays.new-years-day-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.test.test-helper
    [calendar-logic.us-holidays :only [new-years-day]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest new-years-day-test
  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (new-years-day year month day)))
    => [[1971 1 1] [1972 1 1] [1973 1 1] [1974 1 1] [1975 1 1] [1976 1 1] [1977 1 1] [1978 1 1] [1979 1 1]
        [1980 1 1] [1981 1 1] [1982 1 1] [1983 1 1] [1984 1 1] [1985 1 1] [1986 1 1] [1987 1 1] [1988 1 1]
        [1989 1 1] [1990 1 1] [1991 1 1] [1992 1 1] [1993 1 1] [1994 1 1] [1995 1 1] [1996 1 1] [1997 1 1]
        [1998 1 1] [1999 1 1] [2000 1 1] [2001 1 1] [2002 1 1] [2003 1 1] [2004 1 1] [2005 1 1] [2006 1 1]
        [2007 1 1] [2008 1 1] [2009 1 1] [2010 1 1] [2011 1 1] [2012 1 1] [2013 1 1] [2014 1 1] [2015 1 1]
        [2016 1 1] [2017 1 1] [2018 1 1] [2019 1 1] [2020 1 1]])

  (eg
    (run* [year]
      (new-years-day year 1 2))
    => []))
