#_{:clj-kondo/ignore [:namespace-name-mismatch]}
(ns exercises.clojure-3.clojure-3.1-2)

; exercise 1
(defn sum-numbers [nums]
  (map println nums) ; println wird nie ausgeführt
  (reduce + nums))

; Warum wird println nie ausgeführt?
; weil map lazy ist und der wert wird nicht "angefordert" oder weiterverwendet

; Lösung 1:
; println als teil der reduce function ausführen
(defn sum-numbers-with-print-1 [nums]
  (reduce (fn [acc, curr]
            (println curr)
            (+ acc curr)) nums))

; Lösung 2:
; plrintl als side-effect in der map ausführen und mit doall forcieren
(defn sum-numbers-with-print-2 [nums]
  (doall (map println nums)) ; zwingt die Ausführung von map
  (reduce + nums))


(sum-numbers (range 10))
(sum-numbers-with-print-1 (range 10))
(sum-numbers-with-print-2 (range 10))

; exercise 2
; 1)
(mapcat (fn [x] (map #(+ x %) (range 3))) (range 3))
; ->
(for [x (range 3)
      y (range 3)]
  (+ x y))

; 2)
(map #(list % %) (filter #(== (mod % 3) 0) (range 10)))
; ->
(for [x (range 10)
      :when (== (mod x 3) 0)]
  (list x x))

; 3)
(filter #(> (second %) (first %))
        (mapcat (fn [x] (map #(list x %) (range 4))) (range 4)))

; explanation:
;(map #(list x %) (range 4))
;(mapcat (fn [x] (map #(list x %) (range 4))) (range 4))
; ->
(for [x (range 4)
      y (range 4)
      :when (> y x)]
  (list x y))


