(ns datetime-logic.test.test-helper
  (:use clojure.test))

(defmacro eg [subject _ expectation]
  `(is (= ~subject ~expectation)))