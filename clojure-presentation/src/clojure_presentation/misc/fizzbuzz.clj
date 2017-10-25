(ns clojure-repl-experiments.presentation.misc.fizzbuzz)

;;; FizzBuzz

(defn fizzbuzz
  "Returns infinite lazy sequence of numbers starting from 1, replacing any number
  divisible by three with 'Fizz' and any number divisible by five with 'Buzz'.
  Note: number divisible with both three and five will be replaced with 'FizzBuzz'."
  []
  )

(fizzbuzz)

(defn fizzbuzz
  "Returns infinite lazy sequence of numbers starting from 1, replacing any number
  divisible by three with 'Fizz' and any number divisible by five with 'Buzz'.
  Note: number divisible with both three and five will be replaced with 'FizzBuzz'."
  []
  (rest (range)))

(take 20 (fizzbuzz))

(defn- number->str [n]
  (cond
    (zero? (mod n 15)) "FizzBuzz"
    (zero? (mod n 5)) "Buzz"
    (zero? (mod n 3)) "Fizz"
    :else n))

(defn fizzbuzz
  "Returns infinite lazy sequence of numbers starting from 1, replacing any number
  divisible by three with 'Fizz' and any number divisible by five with 'Buzz'.
  Note: number divisible with both three and five will be replaced with 'FizzBuzz'."
  []
  (map number->str
       (rest (range))))

(take 20 (fizzbuzz) )
