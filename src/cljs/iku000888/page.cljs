(ns iku000888.page
  (:require
   [accountant.core :as accountant]
   [secretary.core :as secretary :include-macros true :refer [defroute]]
   [reagent.core :as reagent :refer [atom]]))

(declare foo bar)

(defn page-foo []
  [:div
   "foo"
   [:a {:href (bar)} "bar"]
   ])

(defn page-bar []
  [:div
   "bar"
   [:a {:href (foo)} "foo"]])

(def current-page
  (atom page-foo))

(defn app []
  [:div [@current-page]])

(defroute "/" []
  (accountant/navigate! "#/foo/"))

(defroute foo "/foo/" {:as params}
  (reset! current-page
          page-foo))

(defroute bar "/bar/" {:as params}
  (reset! current-page
          page-bar))


(secretary/set-config! :prefix "#")


(defn init []
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (let [[_ fragment] (re-find #"#(.*)" path)]
        (secretary/dispatch! (or fragment "/"))))
    :path-exists?
    (fn [path]
      (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (reagent/render [app]
                  (.getElementById js/document "app"))
  )

(init)
