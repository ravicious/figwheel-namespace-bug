(ns ^:figwheel-always figwheel-namespace-bug.core
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [reagent.core :as reagent]
            [re-frame.core :refer [register-handler
                                   register-sub
                                   dispatch
                                   subscribe]]
            [figwheel-namespace-bug.interface.handlers]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

(defonce app-state {:text "Click me!"})

(register-handler
  :initialize
  (fn [db _]
    app-state))

(register-sub
  :text
  (fn [db _]
    (reaction (:text @db))))

(defn root []
  (let [text (subscribe [:text])]
    (fn []
      [:div
       [:button
        {:on-click #(dispatch [:button-clicked])}
        @text]])))

(dispatch [:initialize])
(reagent/render [root] (. js/document (getElementById "app")))
