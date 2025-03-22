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