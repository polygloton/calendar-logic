(ns datetime-logic.arithmetic
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic)
  (:require
    [clojure.core.logic.fd :as fd]))

(defn product-pluso [factor1 factor2 number sum]
  (fd/eq (= sum (+ number (* factor1 factor2)))))

(defn div-modo [dividend divisor modulo quotient]
  (!= divisor 0)
  (fd/< modulo divisor)
  (product-pluso quotient divisor modulo dividend))

(defn div-flooro [dividend divisor quotient]
  (fresh [modulo]
    (fd/in modulo (fd/interval 0 Integer/MAX_VALUE))
    (fd/< modulo divisor)
    (!= divisor 0)
    (div-modo dividend divisor modulo quotient)))

(defn modo [dividend divisor modulo]
  (fresh [quotient]
    (fd/in quotient (fd/interval 0 Integer/MAX_VALUE))
      (!= divisor 0)
      (fd/< modulo divisor)
      (div-modo dividend divisor modulo quotient)))