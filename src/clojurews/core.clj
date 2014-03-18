(ns clojurews.core
  (:require [compojure.core :refer [GET POST PUT DELETE ANY defroutes routes]]
            [compojure.handler :as handler]
            [clojure.tools.logging :as log]
            [clojure.pprint :as pp]
            [org.httpkit.server :as hk]
            )
  (:use
    clojurews.env
    clojurews.intercept)
  )

(defn handler [req]
  (hk/with-channel req channel
                   (let [body (String. (.bytes (:body req)))
                         parsed (read-string body)
                         result (with-out-str (pp/pprint parsed))]
                     (do
                       (log/debugf "Pretty Printing Posted Content %s" parsed)
                       (hk/send! channel {:status 200 :headers {"Content-Type" "text/plain"} :body result}))))
  )

(def interceptors (atom nil))



(defroutes all-routes
           (POST ["/pprint/"] req handler)
           (POST ["/pprint_debug/"] req (log-request-headers handler))
           (POST ["/pprint_intercept/"] req @interceptors))



(def app
  (->
    (routes all-routes)
    handler/api
    ))

(defn -main []
  (build-interceptors! handler interceptors)
  (log/infof "Starting REST Server")
  (hk/run-server app {:port 8080}))
