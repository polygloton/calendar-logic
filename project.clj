(defproject calendar-logic "0.0.1-SNAPSHOT"
  :description "Calendar querying"
  :url "http://github.com/polygloton/calendar-logic"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clj-time "0.6.0"]
                 [org.clojure/core.logic "0.8.7"]]
  :test-selectors {:default (fn [m] (not (or (:slow m)
                                            (:broken m)
                                            (:example m))))
                   :slow :slow
                   :broken :broken
                   :example :example
                   :all (constantly true)}
)
