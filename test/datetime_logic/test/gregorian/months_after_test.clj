(ns datetime-logic.test.gregorian.months-after-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.test.test-helper
    [datetime-logic.gregorian :only [months-after]]
    [clojure.core.logic :exclude [is]])
  (:require
    [clojure.core.logic.fd :as fd]))

(deftest months-after-test

  (eg
    (run* [q]
      (months-after :december q))
    => [])

  (eg
    (run* [q]
      (months-after :november q))
    => [:december])

  (eg
    (set
      (run* [q]
        (months-after :october q)))
    => #{:november :december})

  (eg
    (set
      (run* [q]
        (months-after :september q)))
    => #{:october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :august q)))
    => #{:september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :july q)))
    => #{:august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :june q)))
    => #{:july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :may q)))
    => #{:june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :april q)))
    => #{:may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :march q)))
    => #{:april :may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :february q)))
    => #{:march :april :may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :january q)))
    => #{:february :march :april :may :june :july :august :september :october :november :december})

  (eg
    (set
      (run* [q]
        (months-after :october q) (months-after :may q)))
    => #{:november :december}))