(ns tsubasa-lib.core-test
  (:require [clojure.test :refer :all]
            [tsubasa-lib.core :refer :all]
            [schema.core :as s]))

(def test-team-1 "test team 1")

(defn obeys-schema [schema data]
  (let [valid-data (s/validate schema data)]
    (is (= valid-data data))
    valid-data))

(deftest test-goal
  (testing "goal returns a valid Goal"
    (obeys-schema Goal (goal "test")))

  (testing "accepts optional scorer"
    (let [data (obeys-schema Goal (goal test-team-1 :scorer "Player1"))]
      (is (= (-> data :scorer) "Player1")))))

(deftest test-player
  (testing "player returns a valid Player"
    (obeys-schema Player (player "Test"))))

(deftest test-team
  (testing "returns valid team"
    (let [name "test team"
          players [(player "1") (player "2")]]
      (obeys-schema Team (team name players)))))

(deftest test-match
  (testing "returns valid match"
    (let [team1 (team "t1" [(player "1") (player "2")])
          team2 (team "t2" [(player "t2p1") (player "t2p2")])
          data (match team1 team2)]
      (obeys-schema Match data))))

