;p2
;destructuring - DSL for binding names
(defn next-fib-pair [pair]
  [(second pair) (+ (first pair) (second pair))])

;destructuring of sequences uses []
(defn better-next-fib-pair [[a b]]
  [b (+ a b)])

(take 10 (map first (iterate better-next-fib-pair [0 1])))



(require '[clojure.string :as str])
;associative destructuring
;map destructuring preceeds by culy brackets
(defn format-name [person]
  (str/join " " [(:first-name person)
                 (:last-name person)
                 (:job person)])
  )

(format-name {:job "The Architect" :first-name "Ted" :last-name "Mosby"})

(defn better-format-name [{first-name :first-name
                           last-name  :last-name
                           job        :job}]
  (str/join " " [first-name
                 last-name
                 job]))

(better-format-name {:job "The Architect" :first-name "Ted" :last-name "Mosby"})

(defn best-format-name [{:keys [first-name
                                last-name
                                job]}]
  (str/join " " [first-name
                 last-name
                 job]))

(best-format-name {:job "The Architect" :first-name "Ted" :last-name "Mosby"})


;Optional keyword arguments
;not a language feature, composition of varible arity functions and map destructuring

(defn my-connect! [url & {:keys [port protocol]}]
  (println url)
  (println port)
  (println protocol)
  )

(my-connect! "localhost")
(my-connect! "localhost" :protocol "https" :port 9200)
(my-connect! "localhost" :protocol "https")


(def x {:a {:b "c"}})
(-> x :a :b)
(macroexpand '(-> x :a :b))

(require '[clojure.java.io :as io])
