(ns datetime-logic.test-helper
  (:use clojure.test))

(defmacro eg [subject _ expectation]
  `(is (= ~subject ~expectation)))