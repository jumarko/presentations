(ns clojure-presentation.misc.demo)

(defn whats-the-problem? [coll])

;;; Compute the average of all positive numbers
;;; in given collection

[1 0 10 -1 5 0 3 -7 -8 9 0] ;=> 28/5

(defn whats-the-problem? [coll]
  (let [pos-nums (filter pos? coll)]
    (/ (apply + pos-nums)
       (count pos-nums))))
;; let's try
(whats-the-problem? [1 0 10 -1 5 0 3 -7 -8 9 0])
;; OUCH!
(whats-the-problem? [])

;; we can introduce if
(defn whats-the-problem? [coll]
  (let [pos-nums (filter pos? coll)]
    (if (seq pos-nums)
      (/ (apply + pos-nums)
         (count pos-nums))
      ;; NaN or :undefined
      Double/NaN)))
;; let's try
(whats-the-problem? [1 0 10 -1 5 0 3 -7 -8 9 0])
(whats-the-problem? [])

;; we can also make original implementation to return vector
(defn whats-the-problem? [coll]
  (let [pos-nums (filter pos? coll)]
    [(apply + pos-nums) (count pos-nums)]))
;; let's try
(whats-the-problem? [1 0 10 -1 5 0 3 -7 -8 9 0])
(whats-the-problem? [])


;;; more tries:
(whats-the-problem? (range 0 1000001))
[500000500000 1000000]
;; (first + last) * N/2
(* 1000001 500000)
500000500000

;; big collection:
(time (whats-the-problem? (range 0 10000001)))
;;=> "Elapsed time: 5263.310709 msecs"

#_(time (whats-the-problem? (range 0 100000001)))
;;=> ...

;; now with reduce
(defn whats-the-problem? [coll]
  (reduce (fn [[sum count] x]
            [(+ sum x) (inc count)])
          [0 0]
          (filter pos? coll)))

(time (whats-the-problem? (range 0 10000001)))
;;=> "Elapsed time: 1376.000823 msecs"
;;=> [50000005000000 10000000]

(time (whats-the-problem? (range 0 100000001)))
;;=> "Elapsed time: 13171.711098 msecs"
;;=> [5000000050000000 100000000]

#_(time (whats-the-problem? (range 0 1000000001)))
;;=> "Elapsed time: 126161.227183 msecs"
;;=> [500000000500000000 1000000000]


