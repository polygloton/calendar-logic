(ns datetime-logic.core-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    datetime-logic.core
    [clojure.core.logic :exclude [is]]
    [clj-time.core :exclude [extend]]))

(defmacro ie [& body]
  `(is (= ~@body)))

(deftest unifying-date-times-test
  (let [n (now)]
    (ie [(year n)]
      (run* [year]
        (fresh [month day hour minute sec milli]
          (== n [year month day hour minute sec milli]))))

    (ie [(year n)]
      (run* [year]
        (fresh [month day hour minute sec milli]
          (== [year month day hour minute sec milli] n))))

    (ie [(minute n)]
      (run* [minute]
        (fresh [year month day hour sec milli]
          (== n [year month day hour minute sec milli]))))

    (ie [(minute n)]
      (run* [minute]
        (fresh [year month day hour sec milli]
          (== [year month day hour minute sec milli] n))))
  )
)

(deftest date-timeo-test
  (let [n (now)]
    (ie [(year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n)]
      (first
        (run* [year month day hour minute sec milli]
          (date-timeo n year month day hour minute sec milli))))

    (ie [(year n) (month n) (day n) (hour n) (minute n) (sec n)]
      (first
        (run* [year month day hour minute sec]
          (date-timeo n year month day hour minute sec))))

    (ie [(year n) (month n) (day n) (hour n) (minute n)]
      (first
        (run* [year month day hour minute]
          (date-timeo n year month day hour minute))))

    (ie [(year n) (month n) (day n) (hour n)]
      (first
        (run* [year month day hour]
          (date-timeo n year month day hour))))

    (ie [(year n) (month n) (day n)]
      (first
        (run* [year month day]
          (date-timeo n year month day))))

    (ie [(year n) (month n)]
      (first
        (run* [year month]
          (date-timeo n year month))))

    (ie (year n)
      (first
        (run* [year]
          (date-timeo n year))))

    (ie '_0
      (first
        (run* [year]
          (fresh [x]
            (date-timeo x year)))))

    (ie [(year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n)))))

    (ie [(year n) (month n) (day n) (hour n) (minute n) (sec n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n) (minute n) (sec n)))))

    (ie [(year n) (month n) (day n) (hour n) (minute n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n) (minute n)))))

    (ie [(year n) (month n) (day n) (hour n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n)))))

    (ie [(year n) (month n) (day n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n)))))

    (ie [(year n) (month n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n)))))

    (ie [(year n)]
      (first
        (run* [x]
          (date-timeo x (year n)))))
  )
)

(deftest beforeo-test
  (let [dt-1 (plus (now) (minutes 10))
         dt-2 (plus (now) (minutes 15))]
    (ie [dt-1]
      (run* [out]
        (beforeo dt-1 dt-2 out)))

    (ie []
      (run* [out]
        (beforeo dt-2 dt-1 out)))
  )
)

(deftest aftero-test
  (let [dt-1 (plus (now) (minutes 10))
        dt-2 (plus (now) (minutes 15))]
    (ie []
      (run* [out]
        (aftero dt-1 dt-2 out)))

    (ie [dt-2]
      (run* [out]
        (aftero dt-2 dt-1 out)))
  )
)

(deftest presento-test
  (let [n (now)
        dt-1 (date-time (year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n))
        dt-2 (date-time (year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n))]
    (ie [dt-1]
      (run* [out]
        (presento dt-1 dt-2 out)))

    (ie []
      (run* [out]
        (presento dt-1 (plus n (secs 10)) out)))
  )
)

(deftest yearo-test
  (let [n (now)]
    (ie [(hour n)]
      (run* [out]
        (houro n out)))
  )
)

(deftest montho-test
  (let [n (now)]
    (ie [(month n)]
      (run* [out]
        (montho n out)))
  )
)

(deftest dayo-test
  (let [n (now)]
    (ie [(day n)]
      (run* [out]
        (dayo n out)))
  )
)

(deftest houro-test
  (let [n (now)]
    (ie [(hour n)]
      (run* [out]
        (houro n out)))
  )
)

(deftest minuteo-test
  (let [n (now)]
    (ie [(minute n)]
      (run* [out]
        (minuteo n out)))
  )
)

(deftest seco-test
  (let [n (now)]
    (ie [(sec n)]
      (run* [out]
        (seco n out)))
  )
)

(deftest millio-test
  (let [n (now)]
    (ie [(milli n)]
      (run* [out]
        (millio n out)))
  )
)