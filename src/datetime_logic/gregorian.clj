(ns datetime-logic.gregorian
  (:refer-clojure :exclude [== mod])
  (:use
    clojure.core.logic
    datetime-logic.arithmetic)
  (:require
    [clojure.core.logic.fd :as fd]))

(defn leap-year [year]
  (conde
    [(mod year 400 0)]
    [(mod year 4 0) (fresh [mod1] (fd/in mod1 (fd/interval 1 99)) (mod year 100 mod1))]))

(defn standard-year [year]
  (fresh [mod1 mod2 mod3]
    (conde
      [(mod year 100 0) (fd/in mod1 (fd/interval 1 399)) (mod year 400 mod1)]
      [(fd/in mod2 (fd/interval 1 99)) (mod year 100 mod2)
       (fd/in mod3 (fd/interval 1 3)) (mod year 4 mod3)])))

(defn month-before [month-name-after month-name-before]
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

(defn month-after [month-name-before month-name-after]
  (month-before month-name-after month-name-before))

(defn months-before [month-name-after month-name-before]
  (conde
    [(month-before month-name-after month-name-before)]
    [(fresh [temp] (month-before month-name-after temp) (months-before temp month-name-before))]))

(defn months-after [month-name-before month-name-after]
  (conde
    [(month-after month-name-before month-name-after)]
    [(fresh [temp] (month-after month-name-before temp) (months-after temp month-name-after))]))

(defn month-number
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

(defn days-in-february [year days]
  (fd/in days (fd/interval 28 29))
  (conde
    [(standard-year year) (== days 28)]
    [(leap-year year) (== days 29)]))

(defn days-in-month [year month days]
  (conde
    [(== month 1) (== days 31)]
    [(== month 2) (days-in-february year days)]
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

(defn days-in-month-name [year month-name days]
  (fresh [month-num]
    (month-number month-name month-num) (days-in-month year month-num days)))

(defn month-days-in-year [year month-num days]
  (conde
    [(== month-num 0) (== days 0)]
    [(fresh [days-in-this-month last-month-num recur-days]
      (fd/in days-in-this-month (fd/interval 0 31))
      (fd/in last-month-num (fd/interval 0 11))
      (fd/in recur-days (fd/interval 0 366))
      (fd/> month-num 0)
      (days-in-month year month-num days-in-this-month)
      (fd/eq (= last-month-num (- month-num 1)))
      (fd/eq (= days (+ days-in-this-month recur-days)))
      (month-days-in-year year last-month-num recur-days))]))

(defn days-in-year [year month-num day days]
  (conde
    [(== month-num 1) (== day days)]
    [(fresh [last-month-num days-from-months]
       (fd/> month-num 1)
       (fd/in last-month-num (fd/interval 1 11))
       (fd/in days-from-months (fd/interval 31 335))
       (fd/eq (= last-month-num (- month-num 1)))
       (month-days-in-year year last-month-num days-from-months)
       (fd/eq (= days (+ day days-from-months))))]))

(defn fixed-from-gregorian [year month-num day days]
  (fresh [year-less-one leap-4-days leap-100-days leap-400-days
          passed-leap-years-days passed-years-days current-year-days]
    (fd/in year-less-one leap-4-days leap-100-days leap-400-days
           passed-leap-years-days passed-years-days
      (fd/interval 0 Integer/MAX_VALUE))
    (fd/in current-year-days (fd/interval 0 366))
    (fd/eq (= year-less-one (- year 1)))
    (div-floor year-less-one 4 leap-4-days)
    (div-floor year-less-one 100 leap-100-days)
    (div-floor year-less-one 400 leap-400-days)
    (fd/eq (= passed-years-days (* 365 year-less-one)))
    (fd/eq (= passed-leap-years-days (- (+ leap-4-days leap-400-days) leap-100-days)))
    (days-in-year year month-num day current-year-days)
    (fd/eq (= days (+ (+ passed-years-days passed-leap-years-days) current-year-days)))))

(defn day-of-the-week-number [day-name day-number]
  (conde
    [(== day-name :sunday) (== day-number 0)]
    [(== day-name :monday) (== day-number 1)]
    [(== day-name :tuesday) (== day-number 2)]
    [(== day-name :wednesday) (== day-number 3)]
    [(== day-name :thursday) (== day-number 4)]
    [(== day-name :friday) (== day-number 5)]
    [(== day-name :saturday) (== day-number 6)]))

(defn day-of-the-week [year month-num day day-name]
  (fresh [fixed-days day-number]
    (fd/in fixed-days (fd/interval 0 Integer/MAX_VALUE))
    (fd/in day-number (fd/interval 0 6))
    (fixed-from-gregorian year month-num day fixed-days)
    (mod fixed-days 7 day-number)
    (day-of-the-week-number day-name day-number)))