(ns clojure-presentation.00-hello)

(defn greet
  "Returns a friendly greeting"
  [your-name]
  (str "Hello, " your-name))

(greet "FP Brno")

(println (greet "FP Brno"))
