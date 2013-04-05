(ns datetime-logic.test.gregorian.count-month-days-in-yearo-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [count-month-days-in-yearo]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest count-month-days-in-yearo-test

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-month-days-in-yearo 2013 1 q))
    => [31])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-month-days-in-yearo 2013 12 q))
    => [365])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-month-days-in-yearo 2013 10 q))
    => [304])

  (eg
    (run* [q]
      (fd/in q (fd/interval 0 Integer/MAX_VALUE))
      (count-month-days-in-yearo 2013 q 304))
    => [10])
  )