(ns game-of-life.game
  (:require [quil.core :as q :include-macros true]
            [quil.middleware :as m]))

;;; https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
;;; The universe of the Game of Life is an infinite two-dimensional orthogonal
;;; grid of square cells, each of which is in one of two possible states,
;;; alive or dead, or "populated" or "unpopulated".
;;; Every cell interacts with its eight neighbours, which are the cells
;;; that are horizontally, vertically, or diagonally adjacent.
;;; At each step in time, the following transitions occur:
;;; 1 .Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
;;; 2. Any live cell with two or three live neighbours lives on to the next generation.
;;; 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
;;; 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

(def oscillator #{[1 2] [1 1] [1 0]})

(def glider #{[1 3] [2 1] [2 3] [3 2] [3 3]})

(defn neighbors [[x y :as cell]]
  (for [dx [-1 0 1]
        dy [-1 0 1]
        :when (not= dx dy 0)]
    [(+ x dx) (+ y dy)]))

(defn- should-live? [world cell n]
  (or (= n 3)
      (and (= n 2)
           (world cell))))

(defn step [world]
  (set
   (for [[cell n] (frequencies (mapcat neighbors world))
         :when (should-live? world cell n)]
     cell)))

(defn life [initial-world]
  (iterate step initial-world))

(take 5 (life oscillator))
#_(#{[1 0] [1 1] [1 2]}
   #{[0 1] [1 1] [2 1]}
   #{[1 2] [1 1] [1 0]}
   #{[0 1] [1 1] [2 1]}
   #{[1 2] [1 1] [1 0]})

;;; Drawing in browser
(def grid-size 20)

(defn setup []
  (q/frame-rate 2)
  glider)

(defn draw [state]
  (doseq [x (range grid-size)
          y (range grid-size)]
    (if (state [x y])
      (q/fill 255 0 0)
      (q/fill 255 255 255))
    (q/rect (* 20 x) (* 20 y) 20 20)))

(defn update-state [state]
  (step state))

(q/defsketch example
  :host "game-canvas"
  :title "Oh so many grey circles"
  :settings #(q/smooth 2)
  :setup setup
  :update update-state
  :draw draw
  :size [401 401]
  :middleware [m/fun-mode])
