(ns exercises.clojure_3.ex5
  (:require
   [clojure.test :refer [is]]
   [exercises.clojure_3.parser :refer [parse]])
  (:import
   [exercises.clojure_3.parser
    Const
    Func
    Op
    Val
    Var]))

; Beispiel: Baum für (2+3) * 4
(def expr1 (Op. "*" (Op. "+" (Val. 2) (Val. 3)) (Val. 4)))

; Beispiel: Baum für (2+3) * 4, aber mit parse
(def expr2 (parse "(2 + 3) * 4"))

(= expr1 expr2) ; beide Bäume sind gleich

(defn eval-expr [expr env]
  (cond
    (instance? Val expr) (.value expr)
    (instance? Var expr) (get env (.name expr))
    (instance? Const expr)
    (case (.name expr)
      "e" Math/E
      "pi" Math/PI)
    (instance? Op expr)
    (let [left (eval-expr (.left expr) env)
          right (eval-expr (.right expr) env)]
      (case (.op expr)
        "+" (+ left right)
        "-" (- left right)
        "*" (* left right)
        "/" (/ left (double right))
        "^" (Math/pow left right)))
    (instance? Func expr)
    (let [arg (eval-expr (:arg expr) env)]
      (case (:func expr)
        "sin"  (Math/sin arg)
        "cos"  (Math/cos arg)
        "tan"  (Math/tan arg)
        "exp"  (Math/exp arg)
        "log"  (Math/log arg)
        "sqrt" (Math/sqrt arg)))))

; -----------------------------
; Teil a.) Tests mit Argument x
; -----------------------------

(is (== 1 (eval-expr (parse "x") {"x" 1})))
(is (== 2 (eval-expr (parse "x + 2") {"x" 0})))
(is (== 0 (eval-expr (parse "2*x - (x+x)") {"x" 3})))
(is (== 9 (eval-expr (parse "x*x") {"x" 3})))
(is (== 0 (eval-expr (parse "2*x - (1+1)*x") {"x" 2})))
(is (== 2 (eval-expr (parse "2*x - x") {"x" 2})))
(is (== 4 (eval-expr (parse "2*x*x - 3*x + 4") {"x" 0})))

; --------------------------------------------------------------
; Teil b.) Tests zur Ausdrucksauswertung - ganzzahlige Ausdrücke
; --------------------------------------------------------------

(is (== 20 (eval-expr (parse "(2 + 3) * 4") {})))
(is (== 14 (eval-expr (parse "2 + 3 * 4") {})))
(is (== 0 (eval-expr (parse "2 + 3 - 4 + 5 - 6") {})))
(is (== 6 (eval-expr (parse "2*2^2-2") {})))
(is (== -8 (eval-expr (parse "-2^3") {})))
(is (== 4 (eval-expr (parse "(4-2)*(6/3)") {})))
(is (== 262144 (eval-expr (parse "4^3^2") {})))
(is (== 262144 (eval-expr (parse "4^(3^2)") {})))
(is (== 4096 (eval-expr (parse "(4^3)^2") {})))

; ------------------------------------------------------------
; Teil c.) Tests zur Ausdrucksauswertung - Gleitkommaausdrücke
; ------------------------------------------------------------

; Hilfsfunktion für den Vergleich von Gleitkommazahlen
(defn approx= [a b]
  (let [epsilon 1e-6]
    (< (abs (- a b)) epsilon)))

(is (approx= (/ 1 3) (eval-expr (parse "1/3") {})))
(is (approx= 0.5 (eval-expr (parse "1/2") {})))
(is (approx= (/ 1 64) (eval-expr (parse "1/2/4/8") {})))

; Test: Funktionswerte der Funktion x^2 - 2*x + 1 von -10 bis 10
(is (=
     (for [x (range -10 11)] (+ (* x x) (* -2.0 x) 1.0))
     (for [i (range -10 11)] (eval-expr (parse "x^2 - 2*x + 1") {"x" i}))))

; -----------------------------
; Teil d.) Tests mit Konstanten
; -----------------------------

(is (approx= Math/E (eval-expr (parse "e") {})))
(is (approx= Math/PI (eval-expr (parse "pi") {})))

; ----------------------------
; Teil e.) Test mit Funktionen
; ----------------------------

; sqrt
(is (approx= 0 (eval-expr (parse "sqrt(0)") {})))
(is (approx= 1 (eval-expr (parse "sqrt(1)") {})))
(is (approx= 2 (eval-expr (parse "sqrt(4)") {})))
(is (approx= 3 (eval-expr (parse "sqrt(9)") {})))
(is (approx= (Math/sqrt 2) (eval-expr (parse "sqrt(2)") {})))
(is (NaN? (eval-expr (parse "sqrt(-2)") {})))

; sin
(is (approx= 0 (eval-expr (parse "sin(0)") {})))
(is (approx= 1 (eval-expr (parse "sin(pi/2)") {})))
(is (approx= 0 (eval-expr (parse "sin(pi)") {})))
(is (approx= -1 (eval-expr (parse "sin(3*pi/2)") {})))

; cos
(is (approx= 1 (eval-expr (parse "cos(0)") {})))
(is (approx= 0 (eval-expr (parse "cos(pi/2)") {})))
(is (approx= -1 (eval-expr (parse "cos(pi)") {})))
(is (approx= 0 (eval-expr (parse "cos(3*pi/2)") {})))

; tan
(is (approx= 0 (eval-expr (parse "tan(0)") {})))
(is (approx= 1 (eval-expr (parse "tan(pi/4)") {})))
(is (approx= 0 (eval-expr (parse "tan(pi)") {})))

; log und exp
(is (approx= Math/E (eval-expr (parse "exp(3-2)") {})))
(is (approx= 1 (eval-expr (parse "log(e)") {})))
(is (approx= Math/PI (eval-expr (parse "e^log(pi)") {})))
(is (approx= Math/PI (eval-expr (parse "exp(log(pi))") {})))
