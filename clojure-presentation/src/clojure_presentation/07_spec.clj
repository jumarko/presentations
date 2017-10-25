(ns clojure-presentation.06-spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as gen]
            [clojure.spec.test.alpha :as stest]))

(def user-data
  {:user/due-date #inst "2017-04-19T15:47:12.000-00:00"
   :user/purcharser "Acme Moving"
   :user/line-items
   [{:user/item-id 30790 :user/quantity 100}
    {:user/item-id 1375 :user/quantity 2}]})

;; let's create spec for our domain data...
(s/def ::item-id pos-int?)
(s/def ::quantity nat-int?)
(s/def ::line-item (s/keys :req-un [::item-id ::quantity]))

(s/def ::purcharser string?)
(s/def ::line-items (s/coll-of ::line-item :min-count 1 :gen-max 2))
(s/def ::due-date (s/inst-in #inst "2000" #inst "2020"))
(s/def ::order (s/keys :req-un [::purcharser ::line-items] :opt-un [::due-date]))

(gen/generate (s/gen ::order))

{:purcharser "np4V7d0Jqni38"
 :line-items [{:item-id 95, :quantity 1777} {:item-id 1907448, :quantity 66193847}]}

{:purcharser "EMq708dKN0n",
 :line-items [{:item-id 428824, :quantity 4291}],
 :due-date #inst "2000-01-01T00:00:08.245-00:00"}
