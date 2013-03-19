(ns datetime-logic.core
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic)
  (:require
    [clj-time.core :as time]))

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

; Unifying against a DateTime requires that the DateTime be on the left side.
(defn datetimeo [date-time & vars]
  (== date-time vars))
