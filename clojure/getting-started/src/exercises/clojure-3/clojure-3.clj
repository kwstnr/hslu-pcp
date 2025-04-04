(ns exercises.clojure-3.clojure-3)

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
            (do
              (println curr)
              (+ acc curr))) nums))

; Lösung 2:
; plrintl als side-effect in der map ausführen und mit doall forcieren
(defn sum-numbers-with-print 2 [nums]
  (doall (map println nums)) ; zwingt die Ausführung von map
  (reduce + nums))


(sum-numbers (range 10))