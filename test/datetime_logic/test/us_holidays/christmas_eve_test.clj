(ns datetime-logic.test.us-holidays.christmas-eve-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.us-holidays :only [christmas-eve]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest christmas-eve-test
  (eg
    (sort
      (run* [year month day]
        (fd/in year (fd/interval 1971 2020))
        (christmas-eve year month day)))
    => [[1971 12 24] [1972 12 24] [1973 12 24] [1974 12 24] [1975 12 24] [1976 12 24] [1977 12 24] [1978 12 24] [1979 12 24] [1980 12 24] [1981 12 24] [1982 12 24] [1983 12 24] [1984 12 24] [1985 12 24] [1986 12 24] [1987 12 24] [1988 12 24] [1989 12 24] [1990 12 24] [1991 12 24] [1992 12 24] [1993 12 24] [1994 12 24] [1995 12 24] [1996 12 24] [1997 12 24] [1998 12 24] [1999 12 24] [2000 12 24] [2001 12 24] [2002 12 24] [2003 12 24] [2004 12 24] [2005 12 24] [2006 12 24] [2007 12 24] [2008 12 24] [2009 12 24] [2010 12 24] [2011 12 24] [2012 12 24] [2013 12 24] [2014 12 24] [2015 12 24] [2016 12 24] [2017 12 24] [2018 12 24] [2019 12 24] [2020 12 24]])

  (eg
    (run* [year]
      (christmas-eve year 1 1))
    => []))