(ns figwheel-namespace-bug.interface.handlers
  (:require [re-frame.core :refer [register-handler]]))

(def characters (vec (map char (range 33 127))))

(defn- random-string [n]
  (->>
    (repeatedly #(rand-nth characters))
    (take n)
    (apply str)))

(register-handler
  :button-clicked
  (fn [db _]
    (assoc db :text (random-string 10))))
