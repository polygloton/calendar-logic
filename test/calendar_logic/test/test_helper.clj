(ns calendar-logic.test.test-helper
  (:use clojure.test))

(defmacro eg [actual _ expectation]
  `(is (= ~expectation ~actual)))
