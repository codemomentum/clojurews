;intro
(def x (str "Hello" " " "World"))
(println x)

(+ 1 2)

(* 5 (+ 1 2))

(class #{1 2 3})

;COLLECTIONS AND SEQUENCES
(class [1 2 3])
(class '(1 2 3))

(coll? '(1 2 3))
(coll? [1 2 3])

; Only lists are seqs.
(seq? '(1 2 3))
(seq? [1 2 3])


(range 4)
(set! *print-length* 100)
(range)
(take 4 (range))

(cons 4 [1 2 3])
(cons 4 '(1 2 3))

(conj [1 2 3] 4)
(conj '(1 2 3) 4)



(map inc [1 2 3])
(filter even? [1 2 3])
(reduce + [1 2 3 4])

;iteration

(def x (range 10))
(dorun (map #(println %) x))
(dorun (for [a x]
         (println a)
         ))




(require '[clojure.java.io :as io])


;inside out
(reduce + (filter odd? (map inc (range 10))))

(->> (range 10)
     (map inc)
     (filter odd?)
     (reduce +))

(def x {:key1 "val1" :key2 {:key3 "val3"}})
(:key3 (:key2 x))
(-> x
    :key2
    :key3)

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





