(ns datetime-logic.core-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.core
    [clojure.core.logic :exclude [is]])
  (:require
    [clj-time.core :as t]))

(deftest datetime-unification-test
  (let [dt (t/date-time 2013 3 18 9 41 30 30)]
    (is (= '(2013) (run* [year] (== dt [year])))))

  (let [dt (t/date-time 2013 3 18 9 41 30 30)]
    (is (= '([2013 3]) (run* [year month] (== dt [year month])))))

  (let [dt (t/date-time 2013 3 18 9 41 30 30)]
    (is (= '([2013 3 18 9 41 30 30])
           (run* [year month day hour minute second millisecond]
             (== dt [year month day hour minute second millisecond])))))
)

(deftest datetimeo-test
  (let [dt (t/now)]
    (is (= [(t/year dt)] (run* [year] (datetimeo dt year)))))

  (let [dt (t/now)]
    (is (= [[(t/year dt) (t/month dt)]] (run* [year month] (datetimeo dt year month)))))

  (let [dt (t/now)]
    (is (= [[(t/year dt) (t/month dt) (t/day dt) (t/hour dt) (t/minute dt) (t/sec dt) (t/milli dt)]]
           (run* [year month day hour minute second millisecond]
             (datetimeo dt year month day hour minute second millisecond)))))
)