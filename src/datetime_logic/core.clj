(ns datetime-logic.core
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic)
  (:require
    [clj-time.core :as time]
    [clojure.core.logic.fd :as fd]))

; The following is a modification of the extending core.logic with Datomic example
; https://github.com/clojure/core.logic/wiki/Extending-core.logic-%28Datomic-example%29

(defn unify-with-datetime* [u v s]
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

(extend-type org.joda.time.DateTime
  IUnifyTerms
  (unify-terms [u v s]
    (unify-with-datetime* u v s)))

; Unifying against a DateTime requires that the DateTime be on the left side. Normally == shouldn't care about variable
; order.  Using datetimeo instead of == when one unifying a DateTime with a collection of lvars seems safer to me.
(defn datetimeo [date-time & vars]
  (== date-time vars))

(defn gt-yearo
  [dt-1 dt-2 out]
  (fresh [year-1 year-2]
    (datetimeo dt-1 year-1)
    (datetimeo dt-2 year-2)
    (fd/> year-1 year-2)
    (== dt-1 out)))

(defn eq-yearo
  [dt-1 dt-2 out]
  (fresh [year-1 year-2]
    (datetimeo dt-1 year-1)
    (datetimeo dt-2 year-2)
    (== year-1 year-2)
    (== dt-1 out)))

(defn gt-montho
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 year-2 month-2]
    (datetimeo dt-1 year-1 month-1)
    (datetimeo dt-2 year-2 month-2)
    (conde
      [(eq-yearo dt-1 dt-2 dt-1) (fd/> month-1 month-2)]
      [(gt-yearo dt-1 dt-2 dt-1)])
    (== dt-1 out)))

(defn eq-montho
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 year-2 month-2]
    (eq-yearo dt-1 dt-2 dt-1)
    (datetimeo dt-1 year-1 month-1)
    (datetimeo dt-2 year-2 month-2)
    (== month-1 month-2)
    (== out dt-1)))

(defn gt-dateo
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 year-2 month-2 date-2]
    (datetimeo dt-1 year-1 month-1 date-1)
    (datetimeo dt-2 year-2 month-2 date-2)
    (conde
      [(eq-montho dt-1 dt-2 dt-1) (fd/> date-1 date-2)]
      [(gt-montho dt-1 dt-2 dt-1)])
    (== dt-1 out)))

(defn eq-dateo
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 year-2 month-2 date-2]
    (eq-montho dt-1 dt-2 dt-1)
    (datetimeo dt-1 year-1 month-1 date-1)
    (datetimeo dt-2 year-2 month-2 date-2)
    (== date-1 date-2)
    (== out dt-1)))

(defn gt-houro
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 hour-1 year-2 month-2 date-2 hour-2]
    (datetimeo dt-1 year-1 month-1 date-1 hour-1)
    (datetimeo dt-2 year-2 month-2 date-2 hour-2)
    (conde
      [(eq-dateo dt-1 dt-2 dt-1) (fd/> hour-1 hour-2)]
      [(gt-dateo dt-1 dt-2 dt-1)])
    (== dt-1 out)))

(defn eq-houro
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 hour-1 year-2 month-2 date-2 hour-2]
    (eq-dateo dt-1 dt-2 dt-1)
    (datetimeo dt-1 year-1 month-1 date-1 hour-1)
    (datetimeo dt-2 year-2 month-2 date-2 hour-2)
    (== hour-1 hour-2)
    (== out dt-1)))

(defn gt-minuteo
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 hour-1 minute-1 year-2 month-2 date-2 hour-2 minute-2]
    (datetimeo dt-1 year-1 month-1 date-1 hour-1 minute-1)
    (datetimeo dt-2 year-2 month-2 date-2 hour-2 minute-2)
    (conde
      [(eq-houro dt-1 dt-2 dt-1) (fd/> minute-1 minute-2)]
      [(gt-houro dt-1 dt-2 dt-1)])
    (== dt-1 out)))

(defn eq-minuteo
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 hour-1 minute-1 year-2 month-2 date-2 hour-2 minute-2]
    (eq-houro dt-1 dt-2 dt-1)
    (datetimeo dt-1 year-1 month-1 date-1 hour-1 minute-1)
    (datetimeo dt-2 year-2 month-2 date-2 hour-2 minute-2)
    (== minute-1 minute-2)
    (== out dt-1)))

(defn gt-secondo
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 hour-1 minute-1 second-1 year-2 month-2 date-2 hour-2 minute-2 second-2]
    (datetimeo dt-1 year-1 month-1 date-1 hour-1 minute-1 second-1)
    (datetimeo dt-2 year-2 month-2 date-2 hour-2 minute-2 second-2)
    (conde
      [(eq-minuteo dt-1 dt-2 dt-1) (fd/> second-1 second-2)]
      [(gt-minuteo dt-1 dt-2 dt-1)])
    (== dt-1 out)))

(defn eq-secondo
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 hour-1 minute-1 second-1 year-2 month-2 date-2 hour-2 minute-2 second-2]
    (eq-minuteo dt-1 dt-2 dt-1)
    (datetimeo dt-1 year-1 month-1 date-1 hour-1 minute-1 second-1)
    (datetimeo dt-2 year-2 month-2 date-2 hour-2 minute-2 second-2)
    (== second-1 second-2)
    (== out dt-1)))

(defn gt-milliseco
  [dt-1 dt-2 out]
  (fresh [year-1 month-1 date-1 hour-1 minute-1 second-1 millisec-1 year-2 month-2 date-2 hour-2 minute-2 second-2 millisec-2]
    (datetimeo dt-1 year-1 month-1 date-1 hour-1 minute-1 second-1 millisec-1)
    (datetimeo dt-2 year-2 month-2 date-2 hour-2 minute-2 second-2 millisec-2)
    (conde
      [(eq-secondo dt-1 dt-2 dt-1) (fd/> millisec-1 millisec-2)]
      [(gt-secondo dt-1 dt-2 dt-1)])
    (== dt-1 out)))