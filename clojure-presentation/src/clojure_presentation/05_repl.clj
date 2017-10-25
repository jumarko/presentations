(ns clojure-presentation.05-repl)

(defn foo [n]
  (cond
    (> n 40) (+ n 20)
    (> n 20) (= (first n) 20)
    :else 0))

#_(foo 24)
(def n 24)

;; or embedded debugger
