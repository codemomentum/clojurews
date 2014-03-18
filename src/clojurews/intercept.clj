(ns clojurews.intercept
  (:require [clojure.tools.logging :as log])
  (:use clojurews.env)
  )


(defn log-request-headers [handler]
  (fn [request]
    (log/infof "Logging Request Headers: %s" request)
    (try
      (handler request)
      (catch Exception e
        (.printStackTrace e)))))

(defn log-request-body [handler]
  (fn [request]
    (log/infof "Logging Request Body: %s" (String. (.bytes (:body request))))
    (try
      (handler request)
      (catch Exception e
        (.printStackTrace e)))))

(defn resolve-interceptor [h]
  (try
    (let [[ns f] (.split (str h) "/")]
      (log/infof "Resolving interceptor %s " h)
      (require (symbol ns))
      (resolve h))
    (catch Exception e
      (log/errorf "could not load interceptor %s" h)
      (.printStackTrace e))))

(defn build-interceptors! [handler atom]
  (log/info "Resolving Interceptors...")
  (let [functions (remove nil? (map resolve-interceptor (:interceptors props)))
        handlers (reduce #(%2 %1) handler functions)]
    (log/debugf "Functions %s " functions)
    (log/debugf "Handlers %s " handlers)
    (reset! atom (fn [event]
                   (handlers event)))
    )
  )