(ns datetime-logic.test.gregorian.months-aftero-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [months-aftero]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest months-aftero-test

  (eg
    (run* [q]
      (months-aftero :december q))
    => [])

  (eg
    (run* [q]
      (months-aftero :november q))
    => [:december])

  (eg
    (set
      (run* [q]
        (months-aftero :october q)))
    => #{:november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :september q)))
    => #{:october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :august q)))
    => #{:september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :july q)))
    => #{:august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :june q)))
    => #{:july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :may q)))
    => #{:june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :april q)))
    => #{:may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :march q)))
    => #{:april :may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :february q)))
    => #{:march :april :may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :january q)))
    => #{:february :march :april :may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-aftero :october q) (months-aftero :may q)))
    => #{:november :december}))