(ns datetime-logic.test.us-holidays.new-years-eve-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [new-years-eve]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest new-years-day-test
  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (new-years-eve year month day)))
    => [[1971 12 31] [1972 12 31] [1973 12 31] [1974 12 31] [1975 12 31] [1976 12 31] [1977 12 31] [1978 12 31]
        [1979 12 31] [1980 12 31] [1981 12 31] [1982 12 31] [1983 12 31] [1984 12 31] [1985 12 31] [1986 12 31]
        [1987 12 31] [1988 12 31] [1989 12 31] [1990 12 31] [1991 12 31] [1992 12 31] [1993 12 31] [1994 12 31]
        [1995 12 31] [1996 12 31] [1997 12 31] [1998 12 31] [1999 12 31] [2000 12 31] [2001 12 31] [2002 12 31]
        [2003 12 31] [2004 12 31] [2005 12 31] [2006 12 31] [2007 12 31] [2008 12 31] [2009 12 31] [2010 12 31]
        [2011 12 31] [2012 12 31] [2013 12 31] [2014 12 31] [2015 12 31] [2016 12 31] [2017 12 31] [2018 12 31]
        [2019 12 31] [2020 12 31]])

  (eg
    (run* [year]
      (new-years-eve year 12 30))
    => []))