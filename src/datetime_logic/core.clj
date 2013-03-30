(ns datetime-logic.core
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic
    clojure.core.logic.protocols)
  (:require
    [clj-time.core :as time]
    [clojure.core.logic.fd :as fd]))

(defn- date-time? [dt]
  (= (type dt) org.joda.time.DateTime))

; The following extend-type calls are based on the extending core.logic with Datomic example:
; https://github.com/clojure/core.logic/wiki/Extending-core.logic-%28Datomic-example%29

(defn- unify-with-date-time* [u v s]
  (let [fns [time/year time/month time/day time/hour time/minute time/sec time/milli]]
    (when (and (coll? v) (> (count v) 0))
      (loop [fns fns
             v v
             s s]
        (cond
          (empty? v) s
          (empty? fns) s
          :else (when-let [s (unify s (first v) ((first fns) u))]
                  (recur (rest fns) (next v) s)))))))

(defn- unify-date-time-with-terms* [u v s]
  (if (date-time? v)
    (unify-with-date-time* v u s)
    (when (sequential? v)
      (unify-with-sequential* u v s))))

(extend-type org.joda.time.DateTime
  IUnifyTerms
  (unify-terms [u v s]
    (unify-with-date-time* u v s)))

(extend-type clojure.lang.PersistentVector
  IUnifyTerms
  (unify-terms [u v s]
    (if (date-time? v)
      (unify-with-date-time* v u s)
      (when (sequential? v)
        (unify-with-sequential* u v s)))))

(defn date-timeo [dt & vars]
  (== dt vars))

(defmacro year-domain [& years]
  `(fd/in ~@years (fd/interval 1 3000)))

(defmacro month-domain [& months]
  `(fd/in ~@months (fd/interval 1 12)))

(defmacro day-domain [& days]
  `(fd/in ~@days (fd/interval 1 31)))

(defmacro hour-domain [& hours]
  `(fd/in ~@hours (fd/interval 0 23)))

(defmacro minute-domain [& minutes]
  `(fd/in ~@minutes (fd/interval 0 59)))

(defmacro sec-domain [& seconds]
  `(fd/in ~@seconds (fd/interval 0 59)))

(defmacro milli-domain [& millis]
  `(fd/in ~@millis (fd/interval 0 999)))

(defn compare-date-time-with-predo
  [predo dt-1 dt-2 out]
  (fresh [year-1 month-1 day-1 hour-1 minute-1 second-1 millisec-1
          year-2 month-2 day-2 hour-2 minute-2 second-2 millisec-2]
    (year-domain year-1 year-2)
    (month-domain month-1 month-2)
    (day-domain day-1 day-2)
    (hour-domain hour-1 hour-2)
    (minute-domain minute-1 minute-2)
    (sec-domain second-1 second-2)
    (milli-domain millisec-1 millisec-2)
    (== dt-1 [year-1 month-1 day-1 hour-1 minute-1 second-1 millisec-1])
    (== dt-2 [year-2 month-2 day-2 hour-2 minute-2 second-2 millisec-2])
    (conde
      [(predo year-1 year-2)]
      [(== year-1 year-2) (predo month-1 month-2)]
      [(== year-1 year-2) (== month-1 month-2) (predo day-1 day-2)]
      [(== year-1 year-2) (== month-1 month-2) (== day-1 day-2) (predo hour-1 hour-2)]
      [(== year-1 year-2) (== month-1 month-2) (== day-1 day-2) (== hour-1 hour-2) (predo minute-1 minute-2)]
      [(== year-1 year-2) (== month-1 month-2) (== day-1 day-2) (== hour-1 hour-2) (== minute-1 minute-2) (predo second-1 second-2)]
      [(== year-1 year-2) (== month-1 month-2) (== day-1 day-2) (== hour-1 hour-2) (== minute-1 minute-2) (== second-1 second-2) (predo millisec-1 millisec-2)])
    (== dt-1 out)))

(defn beforeo
  [dt-1 dt-2 out]
  (compare-date-time-with-predo fd/< dt-1 dt-2 out))

(defn aftero
  [dt-1 dt-2 out]
  (compare-date-time-with-predo fd/> dt-1 dt-2 out))

(defn presento
  [dt-1 dt-2 out]
  (fresh [year month day hour minute second millisec]
    (== dt-1 [year month day hour minute second millisec])
    (== dt-2 [year month day hour minute second millisec])
    (== dt-1 out)))

(defn yearo
  [dt-1 year]
  (fresh [month day hour minute second millisec]
    (== dt-1 [year month day hour minute second millisec])))

(defn montho
  [dt-1 month]
  (fresh [year day hour minute second millisec]
    (== dt-1 [year month day hour minute second millisec])))

(defn dayo
  [dt-1 day]
  (fresh [year month hour minute second millisec]
    (== dt-1 [year month day hour minute second millisec])))

(defn houro
  [dt-1 hour]
  (fresh [year month day minute second millisec]
    (== dt-1 [year month day hour minute second millisec])))

(defn minuteo
  [dt-1 minute]
  (fresh [year month day hour second millisec]
    (== dt-1 [year month day hour minute second millisec])))

(defn seco
  [dt-1 second]
  (fresh [year month day hour minute millisec]
    (== dt-1 [year month day hour minute second millisec])))

(defn millio
  [dt-1 millisec]
  (fresh [year month day hour minute second]
    (== dt-1 [year month day hour minute second millisec])))