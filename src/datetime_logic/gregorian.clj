(ns datetime-logic.gregorian
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

(defn month-beforeo [month-name-after month-name-before]
  (conde
    [(== month-name-before :january) (== month-name-after :february)]
    [(== month-name-before :february) (== month-name-after :march)]
    [(== month-name-before :march) (== month-name-after :april)]
    [(== month-name-before :april) (== month-name-after :may)]
    [(== month-name-before :may) (== month-name-after :june)]
    [(== month-name-before :june) (== month-name-after :july)]
    [(== month-name-before :july) (== month-name-after :august)]
    [(== month-name-before :august) (== month-name-after :september)]
    [(== month-name-before :september) (== month-name-after :october)]
    [(== month-name-before :october) (== month-name-after :november)]
    [(== month-name-before :november) (== month-name-after :december)]))

(defn month-aftero [month-name-before month-name-after]
  (month-beforeo month-name-after month-name-before))

(defn months-beforeo [month-name-after month-name-before]
  (conde
    [(month-beforeo month-name-after month-name-before)]
    [(fresh [temp] (month-beforeo month-name-after temp) (months-beforeo temp month-name-before))]))

(defn months-aftero [month-name-before month-name-after]
  (conde
    [(month-aftero month-name-before month-name-after)]
    [(fresh [temp] (month-aftero month-name-before temp) (months-aftero temp month-name-after))]))

(defn month-numbero
  [month-name month-number]
  (conde
    [(== month-name :january) (== month-number 1)]
    [(== month-name :february) (== month-number 2)]
    [(== month-name :march) (== month-number 3)]
    [(== month-name :april) (== month-number 4)]
    [(== month-name :may) (== month-number 5)]
    [(== month-name :june) (== month-number 6)]
    [(== month-name :july) (== month-number 7)]
    [(== month-name :august) (== month-number 8)]
    [(== month-name :september) (== month-number 9)]
    [(== month-name :october) (== month-number 10)]
    [(== month-name :november) (== month-number 11)]
    [(== month-name :december) (== month-number 12)]))

(defn days-in-februaryo [year days]
  (fd/in days (fd/interval 28 29))
  (conde
    [(standard-yearo year) (== days 28)]
    [(leap-yearo year) (== days 29)]))

(defn days-in-montho [year month days]
  (conde
    [(== month 1) (== days 31)]
    [(== month 2) (days-in-februaryo year days)]
    [(== month 3) (== days 31)]
    [(== month 4) (== days 30)]
    [(== month 5) (== days 31)]
    [(== month 6) (== days 30)]
    [(== month 7) (== days 31)]
    [(== month 8) (== days 31)]
    [(== month 9) (== days 30)]
    [(== month 10) (== days 31)]
    [(== month 11) (== days 30)]
    [(== month 12) (== days 31)]))

(defn days-in-month-nameo [year month-name days]
  (fresh [month-number]
    (month-numbero month-name month-number) (days-in-montho year month-number days)))

(defn count-month-days-in-yearo [year month-num days]
  (conde
    [(== month-num 0) (== days 0)]
    [(fresh [days-in-this-month last-month-num recur-days]
      (fd/in days-in-this-month (fd/interval 0 31))
      (fd/in last-month-num (fd/interval 0 11))
      (fd/in recur-days (fd/interval 0 366))
      (fd/> month-num 0)
      (days-in-montho year month-num days-in-this-month)
      (fd/eq (= last-month-num (- month-num 1)))
      (fd/eq (= days (+ days-in-this-month recur-days)))
      (count-month-days-in-yearo year last-month-num recur-days))]))

(defn count-days-in-yearo [year month-num day days]
  (conde
    [(== month-num 1) (== day days)]
    [(fresh [last-month-num days-from-months]
       (fd/in last-month-num (fd/interval 1 11))
       (fd/in days-from-months (fd/interval 31 335))
       (fd/> month-num 1)
       (fd/eq (= last-month-num (- month-num 1)))
       (count-month-days-in-yearo year last-month-num days-from-months)
       (fd/eq (= days (+ day days-from-months))))]))