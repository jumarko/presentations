(ns clojure-presentation.04-transducers
  (:require [clojure.core.async :as a]))

;;; Example
(def data [ [1 -1 2] [3 4 5 -2]])

(def pos-values (comp cat (filter pos?)))

(transduce pos-values + data)


;;; Comparison
(time 
 (reduce +
         (filter odd?
                 (map inc
                      (range 1e7)))))

(time 
 (->> (range 1e7)
      (map inc)
      (filter odd?)
      (reduce +)))

(time 
 (transduce
  (comp (map inc) (filter odd?))
  +
  (range 1e7)))


;;; Generality
(def ch (a/chan 10 pos-values))
(a/>!! ch [1 -2 3])
(a/<!! ch)
(a/<!! ch)

