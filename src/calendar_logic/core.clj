(ns calendar-logic.core
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
  (let [fns [time/year time/month time/day time/hour time/minute time/second time/milli]]
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
