(ns clojure-presentation.02-pds)

;;; Vectors

(def v [42 :rabbit [1 2 3]])

(v 1)

(peek v)

(pop v)

(subvec v 1)


;;; Maps
(def m {:a 1 :b 2 :c 3})

(m :b)
(:b m)

(keys m)

(assoc m :d 4 :c 42)

(dissoc m :d)

(merge-with + m {:a 2 :b 3})
(merge m {:a 2 :b 3} {:a 10})


(type {:a 1 :b 2})

(type (hash-map :a 1 :b 2))

(type {:a 1 :b 2 :c 3 :d 4 :e 5 :f 6 :g 7 :h 8})

(type {:a 1 :b 2 :c 3 :d 4 :e 5 :f 6 :g 7 :h 8 :i 9})


;;; Nested structures
(def speaker {:name "Juraj"
              :talk {:topics ["Clojure Fundamentals"
                              "EDN"]}
              :likes #{"climbing" "Clojure"}})
(update-in speaker [:talk :topics] conj "Persistent Data Structures")
