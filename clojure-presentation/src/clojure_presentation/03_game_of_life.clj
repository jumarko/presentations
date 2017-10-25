(ns clojure-repl-experiments.presentation.03-game-of-life)

;;;; https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
;;;; ...
;;;; At each step in time, the following transitions occur:
;;;; 1 .Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
;;;; 2. Any live cell with two or three live neighbours lives on to the next generation.
;;;; 3. Any live cell with more than three live neighbours dies, as if by overpopulation.
;;;; 4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

(def my-world #{[1 2] [1 1] [1 0]})

(def oscillator #{[1 2] [1 1] [1 0]})
(def glider #{[1 3] [2 1] [2 3] [3 2] [3 3]})

(defn still-life [initial-world]
  (repeat initial-world))

(take 3 (still-life my-world))


;;; But we want to evolve

(defn step [world]
  world)

(defn life [initial-world]
  (iterate step initial-world))

(take 3 (life my-world))


;;; and change
(defn neighbors [[x y]]
  (for [dx [-1 0 1]
        dy [-1 0 1]
        :when (not= 0 dx dy)]
    [(+ dx x) (+ dy y)]))

(neighbors [1 1])
(comment 
  ([0 0] [0 1] [0 2]
   [1 0]       [1 2]
   [2 0] [2 1] [2 2]))

(defn step [world]
  (mapcat neighbors world))

(defn life [initial-world]
  (iterate step initial-world))

(take 3 (life my-world))


;;; but we need to choose only those who should live
(defn should-live? [n alive?]
  (or (= 3 n)
      (and (= 2 n) alive?)))

(defn step [world]
  (set 
   (map (fn [[cell _]] cell)
        (filter (fn [[cell freq]]
                  (should-live? freq (world cell)))
                (frequencies
                 (mapcat neighbors world))))))

(defn life [initial-world]
  (iterate step initial-world))

(take 3 (life my-world))
(#{[1 0] [1 1] [1 2]}
 #{[1 1] [2 1] [0 1]}
 #{[1 0] [1 1] [1 2]})


;;; everything works now but is a little bit messy

(defn step [world]
  (->> world
       (mapcat neighbors)
       frequencies
       (filter (fn [[cell freq]]
                 (should-live? freq (world cell))))
       (map (fn [[cell _]] cell))
       set))

(take 3 (life my-world))


;;; we can do better

(defn step [world]
  (set
   (for [[cell freq] (frequencies (mapcat neighbors world))
         :when (should-live? freq (world cell))]
     cell)))

(take 3 (life my-world))
(#{[1 0] [1 1] [1 2]}
 #{[1 1] [2 1] [0 1]}
 #{[1 0] [1 1] [1 2]})
