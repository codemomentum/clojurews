(ns clojurews.env
  (:require [clojure.java.io :as io]))

(defn load-props [filename]
  (with-open [r (io/reader filename)]
    (binding [*read-eval* false] (read (java.io.PushbackReader. r)))
    ))

(def props (load-props (-> "config.edn" io/resource)))