(ns exercises.clojure_3.ex_4.ex4
  (:require
   [exercises.clojure_3.ex_4.parser :refer [parse]])
  (:import
   [exercises.clojure_3.ex_4.parser Op Val]))

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
