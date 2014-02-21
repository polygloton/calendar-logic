(ns calendar-logic.test.core-test
  (:refer-clojure :exclude [==])
  (:use
    clojure.test
    calendar-logic.core
    [clojure.core.logic :exclude [is]]
    [clj-time.core :exclude [extend]]))

(defmacro eg [& body]
  `(is (= ~@body)))

(deftest unifying-date-times-test
  (let [n (now)]
    (eg [(year n)]
      (run* [year]
        (fresh [month day hour minute sec milli]
          (== n [year month day hour minute sec milli]))))

    (eg [(year n)]
      (run* [year]
        (fresh [month day hour minute sec milli]
          (== [year month day hour minute sec milli] n))))

    (eg [(minute n)]
      (run* [minute]
        (fresh [year month day hour sec milli]
          (== n [year month day hour minute sec milli]))))

    (eg [(minute n)]
      (run* [minute]
        (fresh [year month day hour sec milli]
          (== [year month day hour minute sec milli] n))))
  )
)

(deftest date-timeo-test
  (let [n (now)]
    (eg [(year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n)]
      (first
        (run* [year month day hour minute sec milli]
          (date-timeo n year month day hour minute sec milli))))

    (eg [(year n) (month n) (day n) (hour n) (minute n) (sec n)]
      (first
        (run* [year month day hour minute sec]
          (date-timeo n year month day hour minute sec))))

    (eg [(year n) (month n) (day n) (hour n) (minute n)]
      (first
        (run* [year month day hour minute]
          (date-timeo n year month day hour minute))))

    (eg [(year n) (month n) (day n) (hour n)]
      (first
        (run* [year month day hour]
          (date-timeo n year month day hour))))

    (eg [(year n) (month n) (day n)]
      (first
        (run* [year month day]
          (date-timeo n year month day))))

    (eg [(year n) (month n)]
      (first
        (run* [year month]
          (date-timeo n year month))))

    (eg (year n)
      (first
        (run* [year]
          (date-timeo n year))))

    (eg '_0
      (first
        (run* [year]
          (fresh [x]
            (date-timeo x year)))))

    (eg [(year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n) (minute n) (sec n) (milli n)))))

    (eg [(year n) (month n) (day n) (hour n) (minute n) (sec n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n) (minute n) (sec n)))))

    (eg [(year n) (month n) (day n) (hour n) (minute n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n) (minute n)))))

    (eg [(year n) (month n) (day n) (hour n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n) (hour n)))))

    (eg [(year n) (month n) (day n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n) (day n)))))

    (eg [(year n) (month n)]
      (first
        (run* [x]
          (date-timeo x (year n) (month n)))))

    (eg [(year n)]
      (first
        (run* [x]
          (date-timeo x (year n)))))
  )
)
