(require '[hiccup.page :refer [include-js include-css html5]]
         '[hiccup.core :as h]
         '[figwheel-sidecar.repl-api :as ra])

;; this will start figwheel and will start autocompiling the builds specified in `:builds-ids`
(def cfg
  {:figwheel-options {}
   :build-ids ["dev"]   ;; <-- a vector of build ids to start autobuilding
   :all-builds   ;; <-- supply your build configs here
   [{:id "dev"
     :figwheel true
     :source-paths ["src/cljs"]
     :compiler {:main "iku000888.page"
                :asset-path "resources/public/out"
                :output-to "resources/public/main.js"
                :output-dir "resources/public/out"
                :verbose true}}]})

(def head
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   [:title "Ikuru"]
   (include-css "/css/main.css")])

(def page
  (html5
   head
   [:body {:class "body-container"}
    [:div#app]
    (include-js "resources/public/main.js")]))

(defn go []
  (spit "index.html" page)
  (ra/start-figwheel! cfg))

(defn clean []
  (ra/clean-builds))

(defn stop []
  (ra/stop-figwheel!))

(defn cljs []
  (ra/cljs-repl))
