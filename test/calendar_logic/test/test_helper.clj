(ns calendar-logic.test.test-helper
  (:use clojure.test))

(defmacro eg
  ([actual _ expectation]
     `(is (= ~expectation ~actual)))
  ([actual expectation]
     `(is (= ~expectation ~actual))))
