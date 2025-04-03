(ns hello.hello)

(+ 1 1)

(+ 1 (* 2 3))

(* 5 3)

(type (* 21 2.0))

(/ 16 2 2 2 2)

(< 1 2 3 4 4)

(<= 1 2 3 4 4)

(and 1 42 17.0 true "hello")

(and "test" nil 0)

(and 0 1)

(str "Hello" "World")

(str 1 2)

(def x '(+ 2 3 4 5))

(eval x)


; profit calculation
(def fixed 250)
(defn amount-of-visitors [ticket-price] (+ 120 (* 30 (- 8.0 ticket-price))))
(defn variable-costs [amount-of-visitors] (* 0.75 amount-of-visitors))
(defn total-costs [ticket-price] (+ fixed (variable-costs (amount-of-visitors ticket-price))))
(defn einnahmen [ticket-price] (* ticket-price (amount-of-visitors ticket-price)))
(defn profit [ticket-price] (- (einnahmen ticket-price) (total-costs ticket-price)))

(profit 8)
(profit 7)
(profit 6)
(profit 5)

(rational? 1/2)

(defn length [lst]
  (if (empty? lst)
    0
    (inc (recur (rest lst)))))

(defn length-2 [lst acc]
  (if (empty? lst)
    acc
    (recur (rest lst) (inc acc))))

(defn length-4 [lst]
  (loop [local-list lst
         acc 0]
    (if (empty? local-list)
      acc
      (recur (rest local-list) (inc acc)))))

(length 10000)

(defn double [start
              length]
  (loop [i 0
         acc (cons start nil)]
    (if (== i length)
      acc
      (recur (inc i) (concat acc (cons (* 2 (first acc)) nil))))))

(double 1 8)

(fn? (fn [x] x))

(* 1 2 2 2)

(- 1 1)

(some #(= % 5) [1 2 3 4 5])

(defn hashtag-plus [a b] (+ a b))

(reduce #(+ %1 %2) [1 2 3])
(map #(+ %1 %2) [[1 1] [2 2] [3 3]])

(map (fn [x] (apply #(+ %1 %2) x)) [[1 1] [2 2] [3 3]])
(map #(apply #(+ %1 %2) %) [[1 1] [2 2] [3 3]])
(map #(+ %1 %2) [1 2 3] [1 2 3])

(take-while even? [2 4 6 7 8 10])
(drop-while even? [2 4 6 7 8 10])
(some even? [2 4 6 7 8 10])


(defn sum-numbers [nums]
  (map println nums) ; println wird nie ausgeführt
  (reduce + nums))

(defn sum-numbers-with-print [nums]
  (reduce (fn [acc, curr]
            (do
              (println curr)
              (+ acc curr))) nums))

(sum-numbers (range 10))
(sum-numbers-with-print (range 10))