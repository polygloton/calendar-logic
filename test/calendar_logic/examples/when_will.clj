(ns calendar-logic.examples.when-will
  (:refer-clojure :exclude [==])
  (:use calendar-logic.test.test-helper
        clojure.test
        [clojure.core.logic :exclude [is]])
  (:require [calendar-logic.gregorian :as greg]
            [calendar-logic.us-holidays :as hol]
            [clojure.core.logic.fd :as fd]))

(deftest ^:example when-will-feb-29-be-tuesday?
  (eg
   (sort
    (run* [year month day dow]
          (fd/in year (fd/interval 2000 3000))
          (== month 2)
          (== day 29)
          (== dow :tuesday)
          (greg/day-in-month year month day)
          (greg/day-of-the-week year month day dow)))
   [[2000 2 29 :tuesday] [2028 2 29 :tuesday] [2056 2 29 :tuesday]
    [2084 2 29 :tuesday] [2124 2 29 :tuesday] [2152 2 29 :tuesday]
    [2180 2 29 :tuesday] [2220 2 29 :tuesday] [2248 2 29 :tuesday]
    [2276 2 29 :tuesday] [2316 2 29 :tuesday] [2344 2 29 :tuesday]
    [2372 2 29 :tuesday] [2400 2 29 :tuesday] [2428 2 29 :tuesday]
    [2456 2 29 :tuesday] [2484 2 29 :tuesday] [2524 2 29 :tuesday]
    [2552 2 29 :tuesday] [2580 2 29 :tuesday] [2620 2 29 :tuesday]
    [2648 2 29 :tuesday] [2676 2 29 :tuesday] [2716 2 29 :tuesday]
    [2744 2 29 :tuesday] [2772 2 29 :tuesday] [2800 2 29 :tuesday]
    [2828 2 29 :tuesday] [2856 2 29 :tuesday] [2884 2 29 :tuesday]
    [2924 2 29 :tuesday] [2952 2 29 :tuesday] [2980 2 29 :tuesday]]))
