(ns pinger.core-test
  (:require [clojure.test :refer :all]
            [pinger.core :refer :all]))

(deftest initialize-person-test
  (testing "Person that has never been pinged gets 0 pings"
    (is (= {:name "foo bar" :times-pinged 0}
           (initialize-person "foo bar"))))
  (testing "Person that has already been pinged"
    (is (= {:name "foo bar" :times-pinged 30}
           (initialize-person "foo bar, 30")))))

(deftest find-least-pinged-test
  (testing "Return a seq with the people with the smallest number of pings"
    (is (= [{:name "foo bar" :times-pinged 0}
            {:name "baz" :times-pinged 0}]
           (find-least-pinged [{:name "foo bar"  :times-pinged 0}
                               {:name "baz"      :times-pinged 0}
                               {:name "John Doe" :times-pinged 3}
                               {:name "Jane Roe" :times-pinged 39}])))))

(deftest read-people-test
  (testing "Read a file and return a sequence with an array of maps"
    (is (= [{:name "foo bar"  :times-pinged 0}
            {:name "John Doe" :times-pinged 3}
            {:name "Jane Roe" :times-pinged 39}]
           (read-people "test/data/small-test-list")))))
