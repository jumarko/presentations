(ns clojure-presentation.01-edn)


;; Simple Types
{
 :string "hello"
 :character \f
 :integer 42
 :floating-point 3.14
 :boolean true
 :nil nil
 :symbol +
 :keyword [:foo ::foo]
 }

;; Collection types
{
 :list '(1 2 3)
 :vector [1 2 3]
 :map {:a 1 :b 2 :c 3}
 :set #{:a :b :c}
 }

;; Built-in tagged literals
{
 :date #inst "2017-10-24"
 :uuid #uuid "98918bf3-19fd-45df-a461-4fb718731fbd"
 }
(java.util.Date.)
(java.util.UUID/randomUUID)

;; Custom tagged literals
(defrecord Car [year model price])

(defn read-car [car-map]
  (map->Car car-map))

(clojure.edn/read-string
 {:readers {'car read-car}}
 "#car{:year 2017, :model \"Aston Martin V8 Vantage\", :price 3000000}")
