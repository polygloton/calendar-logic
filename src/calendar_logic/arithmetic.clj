(ns calendar-logic.arithmetic
  (:refer-clojure :exclude [== mod])
  (:use
    clojure.core.logic)
  (:require
    [clojure.core.logic.fd :as fd]))

(defn product-plus [factor1 factor2 number sum]
  (fd/eq (= sum (+ number (* factor1 factor2)))))

(defn div-mod [dividend divisor modulo quotient]
  (!= divisor 0)
  (fd/< modulo divisor)
  (product-plus quotient divisor modulo dividend))

(defn div-floor [dividend divisor quotient]
  (fresh [modulo]
    (fd/in modulo (fd/interval 0 Integer/MAX_VALUE))
    (fd/< modulo divisor)
    (!= divisor 0)
    (div-mod dividend divisor modulo quotient)))

(defn mod [dividend divisor modulo]
  (fresh [quotient]
    (fd/in quotient (fd/interval 0 Integer/MAX_VALUE))
      (!= divisor 0)
      (fd/< modulo divisor)
      (div-mod dividend divisor modulo quotient)))
