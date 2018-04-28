(require '[hiccup.page :refer [include-js include-css html5]]
         '[cljs.build.api :as b])

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

(spit "index.html" page)
(b/build "src/cljs"
         {:output-to "resources/public/main.js"
          :optimizations :advanced
          :pretty-print  false
          :verbose true})
