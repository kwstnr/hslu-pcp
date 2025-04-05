(ns exercises.clojure_3.ex_4.ex4
  (:require
   [exercises.clojure_3.parser :refer [parse]])
  (:import
   [exercises.clojure_3.parser Op Val]))

;; a)
;;        +
;;      /   \
;;    *      / 
;;   / \    / \
;;  2   -  6   3
;;     / \
;;    4   2

;; b)
(def solution (Op. "+"
                   (Op. "*"
                        (Val. 2)
                        (Op. "-"
                             (Val. 4)
                             (Val. 2)))
                   (Op. "/"
                        (Val. 6)
                        (Val. 3))))

;; c)
(def parsed (parse "2 * (4-2) + (6/3)"))

;; tests
(= parsed solution)

;; d)
(defn eval-expr [expr]
  (cond
    (instance? Val expr) (.value expr)
    (instance? Op expr)
    (let [left (eval-expr (.left expr))
          right (eval-expr (.right expr))]
      (case (.op expr)
        "+" (+ left right)
        "-" (- left right)
        "*" (* left right)
        "/" (/ left right)))))

(eval-expr parsed)
(eval-expr solution)

;; e)
(defn compile-expr [expr]
  (cond
    (instance? Val expr) [(str "LOAD " (.value expr))]
    (instance? Op expr)
    (let [left (compile-expr (.left expr))
          right (compile-expr (.right expr))
          op (case (.op expr)
               "+" ["ADD"]
               "-" ["SUB"]
               "*" ["MUL"]
               "/" ["DIV"])]
      (concat left right op))))

(count (compile-expr parsed))
(compile-expr solution)
