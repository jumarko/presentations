(ns clojure-presentation.03-seqs)


;;; Basic primitives

(first [1 2 3])

(rest [1 2 3])

(cons 0 [1 2 3])
(conj [1 2 3] 0)


;;; Building on top of primitives

;; show source code for take
(take 3 [1 2 3 4 5])

(drop 3 [1 2 3 4 5])

(every? odd? [1 2 3 5])


;;; map, filter, reduce

(map inc (range 10))

(filter odd? (range 10))

(reduce + (range 10))


;;; lazy and infinite

;; to limit our output
(set! *print-length* 13)

(iterate inc 0)

(cycle [1 2])

(repeat :d)

(set! *print-length* nil)

(map + [1 2 3] (iterate inc 1))


;;; Works everywhere

(defn line-count [file]
  (count (line-seq (clojure.java.io/reader file))))
(line-count "/Users/jumar/workspace/clojure/presentations/clojure-presentation/src/clojure_presentation/03_seqs.clj")
(defn file-counts [dir]
  (map line-count
       (filter #(. % isFile)
               (file-seq (clojure.java.io/file dir)))))
(reduce +
        (file-counts "/Users/jumar/workspace/clojure/presentations/clojure-presentation/src/clojure_presentation/"))

