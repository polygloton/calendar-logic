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

(deftest gt-yearo-test
  (let [dt-1 (t/date-time 2012 1 1 12 0 0)
        dt-2 (t/date-time 2013 1 1 12 0 0)]
    (is
      (=
        [dt-2]

        (run* [out]
          (gt-yearo dt-2 dt-1 out))))

    (is
      (=
        []

        (run* [out]
          (gt-yearo dt-1 dt-2 out))))

    (is
      (=
        [true]

        (run* [q]
          (gt-yearo dt-2 dt-1 dt-2) (== q true))))
  )
)

(deftest gt-montho-test
  (let [dt-1 (t/date-time 2012 1 1 12 0 0)
        dt-2 (t/date-time 2013 1 1 12 0 0)
        dt-3 (t/date-time 2013 2 1 12 0 0)]

    (is
      (=
        [dt-3]

        (run* [out]
          (gt-montho dt-3 dt-2 out))))

    (is
      (=
        [dt-2]

        (run* [out]
          (gt-montho dt-2 dt-1 out))))

    (is
      (=
        []

        (run* [out]
          (gt-montho dt-1 dt-2 out))))
  )
)

(deftest gt-dateo-test
  (let [dt-1 (t/date-time 2012 1 1 12 0 0)
        dt-2 (t/date-time 2013 1 1 12 0 0)
        dt-3 (t/date-time 2013 2 1 12 0 0)
        dt-4 (t/date-time 2013 2 2 12 0 0)]

    (is
      (=
        [dt-4]

        (run* [out]
          (gt-dateo dt-4 dt-3 out))))

    (is
      (=
        [dt-3]

        (run* [out]
          (gt-dateo dt-3 dt-2 out))))

    (is
      (=
        []

        (run* [out]
          (gt-dateo dt-1 dt-2 out))))
  )
)

(deftest gt-houro-test
  (let [dt-3 (t/date-time 2013 2 1 12 0 0)
        dt-4 (t/date-time 2013 2 2 12 0 0)
        dt-5 (t/date-time 2013 2 2 13 0 0)]

    (is
      (=
        [dt-5]

        (run* [out]
          (gt-houro dt-5 dt-4 out))))

    (is
      (=
        [dt-4]

        (run* [out]
          (gt-houro dt-4 dt-3 out))))

    (is
      (=
        []

        (run* [out]
          (gt-houro dt-4 dt-5 out))))
  )
)

(deftest gt-minuteo-test
  (let [dt-6 (t/date-time 2013 2 2 13 10 0)
        dt-7 (t/date-time 2013 2 2 14 10 0)
        dt-8 (t/date-time 2013 2 2 14 20 0)]

    (is
      (=
        [dt-8]

        (run* [out]
          (gt-minuteo dt-8 dt-7 out))))

    (is
      (=
        [dt-7]

        (run* [out]
          (gt-minuteo dt-7 dt-6 out))))

    (is
      (=
        []

        (run* [out]
          (gt-minuteo dt-7 dt-8 out))))
  )
)

(deftest gt-secondo-test
  (let [dt-9  (t/date-time 2013 2 2 14 20 5)
        dt-10 (t/date-time 2013 2 2 14 30 5)
        dt-11 (t/date-time 2013 2 2 14 30 10)]

    (is
      (=
        [dt-11]

        (run* [out]
          (gt-secondo dt-11 dt-10 out))))

    (is
      (=
        [dt-10]

        (run* [out]
          (gt-secondo dt-10 dt-9 out))))

    (is
      (=
        []

        (run* [out]
          (gt-secondo dt-10 dt-11 out))))
  )
)

(deftest gt-milliseco-test
  (let [dt-12 (t/date-time 2013 2 2 14 30 10 5)
        dt-13 (t/date-time 2013 2 2 14 30 20 5)
        dt-14 (t/date-time 2013 2 2 14 30 20 10)]

    (is
      (=
        [dt-14]

        (run* [out]
          (gt-milliseco dt-14 dt-13 out))
      )
    )

    (is
      (=
        [dt-13]

        (run* [out]
          (gt-milliseco dt-13 dt-12 out))
      )
    )

    (is
      (=
        []

        (run* [out]
          (gt-milliseco dt-13 dt-14 out))
      )
    )
  )
)