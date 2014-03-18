(ns clojurews.core-test
  (:import (org.httpkit BytesInputStream))
  (:require [clojure.test :refer :all]
            [clojurews.core :refer :all]
            [org.httpkit.server :as hk]))

(def posted (.getBytes "{:url \"http://www.searchly.com/\" :max_urls_per_cycle 100 :es {:url \"http://localhost:9200/\" :index \"index1\" :type \"document\"} :url_include_patterns [\"posts\"] :url_exclude_patterns [\"Catalog\" \"catalog\"]}"))

(def expected "{:url \"http://www.searchly.com/\",\n :max_urls_per_cycle 100,\n :es\n {:url \"http://localhost:9200/\", :index \"index1\", :type \"document\"},\n :url_include_patterns [\"posts\"],\n :url_exclude_patterns [\"Catalog\" \"catalog\"]}\n")


(deftest pprint-test
  (testing "Pretty Print"
    (is (= expected (handler {:body (BytesInputStream. posted (alength posted))})))
    ))
