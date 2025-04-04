(ns exercises.clojure-3.clojure-3.3)

;; a) 
;;  ist nicht funktional -> 
;;    es ist nicht mehr "immutable" oder nebeneffektfrei
;;    die funktionen sind nicht mehr idempotent

(def my-phone-dir
  (atom
   (list (hash-map :name 'Adam :phone 4711)
         (hash-map :name 'Eva :phone 4712))))

(defn look-at [phone-dir name]
  (let [entry (first (filter #(= (:name %) name) @phone-dir))]
    (and entry
         (:phone entry))))

(defn add-entry [phone-dir name phone]
  (swap! phone-dir conj (hash-map :name name :phone phone)))

(look-at my-phone-dir 'Adam) ; 4711
(look-at my-phone-dir 'Eva) ; 4712
(look-at my-phone-dir 'Alex) ; nil
(add-entry my-phone-dir 'Alex 4713) ; nil
(look-at my-phone-dir 'Alex) ; 4713



