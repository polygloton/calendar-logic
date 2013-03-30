(ns datetime-logic.gregorian.years
  (:refer-clojure :exclude [==])
  (:use
    clojure.core.logic
    datetime-logic.arithmetic)
  (:require
    [clojure.core.logic.fd :as fd]))

(defn leap-yearo [year]
  (conde
    [(modo year 400 0)]
    [(modo year 4 0) (fresh [mod] (fd/in mod (fd/interval 1 99)) (modo year 100 mod))]))

(defn standard-yearo [year]
  (fresh [mod1 mod2 mod3]
    (conde
      [(modo year 100 0) (fd/in mod1 (fd/interval 1 399)) (modo year 400 mod1)]
      [(fd/in mod2 (fd/interval 1 99)) (modo year 100 mod2)
       (fd/in mod3 (fd/interval 1 3)) (modo year 4 mod3)])))
