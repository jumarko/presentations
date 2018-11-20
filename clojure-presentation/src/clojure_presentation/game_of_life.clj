(ns clojure-presentation.presentation.game-of-life
  (:require [quil.core :as q]))

;;; http://clj-me.cgrand.net/2011/08/19/conways-game-of-life/

(def my-world #{[1 2] [1 1] [1 0]})

(defn neighbors [[x y :as cell]]
  (for [dx [-1 0 1]
        dy [-1 0 1]
        :when (not= dx dy 0)]
    [(+ x dx) (+ y dy)]))

#_(neighbors [1 1])
;;=> ([0 0] [0 1] [0 2]
;;    [1 0]       [1 2]
;;    [2 0] [2 1] [2 2])

(defn- should-live? [n alive?]
  (or (= n 3)
      (and (= n 2) alive?)))

(defn step [world]
  (set
   (for [[cell n] (frequencies (mapcat neighbors world))
         :when (should-live? n world)]
     cell)))

(defn life [initial-world]
  (iterate step initial-world))

(take 5 (life my-world))
#_(#{[1 0] [1 1] [1 2]}
   #{[0 1] [1 1] [2 1]}
   #{[1 2] [1 1] [1 0]} 
   #{[0 1] [1 1] [2 1]} 
   #{[1 2] [1 1] [1 0]})


;;; quil visualisation
(comment

  ;; explore quil api
  (q/show-cats )

  (defn setup []
    (q/frame-rate 1)                    
    #_(q/background 200))                 
                                      
  (defn draw []
    (q/stroke (q/random 255))             
    (q/stroke-weight (q/random 10))       
    (q/fill (q/random 255))               

    (let [diam (q/random 100)             
          x    (q/random (q/width))       
          y    (q/random (q/height))]     
      (q/ellipse x y diam diam)))         


  (q/defsketch example                  
    :title "Game of Life"    
    :settings #(q/smooth 2)             
    :setup setup                        
    :draw draw                          
    :size [323 200])

  )
