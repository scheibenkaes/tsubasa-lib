(ns tsubasa-lib.core
  (:require [schema.core :as s]))


;; schemas
(def Name s/Str)

(def Goal {:for-team Name})

(def Player {:name Name})

(def Team {:name Name
           :players [Player]})

(def Match {:teams [Team]
            :goals [Goal]})

;; generator fns

(defn match
  "create a instance of Match"
  [team1 team2]
  {:teams [team1 team2]
   :goals []})

(defn team [name players]
  "create a instance of Team"
  {:name name :players players})

(defn player
  "create a new instance of Player"
  [name]
  {:name name})

(defn goal
  "create a new instance of Goal"
  [team]
  {:for-team team})
