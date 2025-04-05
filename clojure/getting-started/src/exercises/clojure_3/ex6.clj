(ns exercises.clojure_3.ex6
  (:require
   [exercises.clojure_3.ex5 :refer [eval-expr]]
   [exercises.clojure_3.parser :refer [parse]])
  (:import
   (java.awt BorderLayout Color Dimension)
   (java.awt.event ActionListener)
   (javax.swing
    JButton
    JFrame
    JPanel
    JTextField)))

(def width 800)
(def height 800)

(defn plot [g func]
  (let [scale 20 ; 1 Einheit = 20 Pixel
        center-x (/ width 2)
        center-y (/ height 2)
        env-fn (fn [x] (eval-expr func {"x" x}))]

    (loop [px -200]
      (when (< px 200)
        (let [x1 (/ px scale)
              y1 (env-fn x1)
              x2 (/ (+ px 1) scale)
              y2 (env-fn x2)

              sx1 (+ center-x px)
              sy1 (- center-y (* y1 scale))
              sx2 (+ center-x (inc px))
              sy2 (- center-y (* y2 scale))]
          (try
            (.drawLine g (int sx1) (int sy1) (int sx2) (int sy2))
            (catch Exception _ nil)))
        (recur (inc px))))))

(defn build-plot-panel [func]
  (proxy [JPanel] []
    ; paintComponent überschreiben, um die Funktion zu zeichnen  
    (paintComponent [g]
      (proxy-super paintComponent g)

      ; --------------------------------
      ; TODO zeichne weissen Hintergrund
      (.setColor g Color/WHITE)
      (.fillRect g 0 0 width height)

      ; TODO zeichne x und y-Achse
      (.setColor g Color/BLACK)
      (.drawLine g 0 (/ height 2) width (/ height 2)) ; x-Achse
      (.drawLine g (/ width 2) 0 (/ width 2) height) ; y-Achse

      ; TODO plotte die Funktion (siehe plot-Funktion)
      ; --------------------------------
      (.setColor g Color/BLUE)
      (plot g @func))

    (getPreferredSize []
      (Dimension. width height))))

(defn plot-frame
  "Create a JFrame with a plot area and a text field for input."
  []
  (let
   [function (atom (parse "x^2"))          ; Atom für die Funktion
    plot-area (build-plot-panel function)  ; Panel für die Funktion
    text-field (JTextField. "x^2")         ; Textfeld für die Eingabe
    button (JButton. "Plot")               ; Button zum Zeichnen
    input-panel (JPanel.)                  ; Panel für Button und Textfeld

    ; erstelle JFrame
    ; und implementiere ActionListener
    ; für den Button
    frame (proxy [JFrame ActionListener] ["Function plotter"]
            (actionPerformed [e]
              (try
                (reset! function (parse (.getText text-field)))
                (.repaint plot-area)
                (catch Exception ex
                  (javax.swing.JOptionPane/showMessageDialog
                   nil (str "Fehler beim Parsen: " (.getMessage ex)))))))]

    (doto button
      ; Button mit ActionListener verbinden
      (.addActionListener frame)
      (.setFocusable true))

    (doto input-panel
      ; Button und Textfeld in Input-Panel hinzufügen
      (.setLayout (BorderLayout.))
      (.add text-field BorderLayout/CENTER)
      (.add button BorderLayout/EAST))

    (doto frame
      ; JFrame einrichten und anzeigen
      (.setLayout (BorderLayout.))
      (.setResizable false)
      (.add plot-area BorderLayout/CENTER)
      (.add input-panel BorderLayout/SOUTH)
      (.pack)
      (.setVisible true))))

; Fenster anzeigen
(plot-frame)