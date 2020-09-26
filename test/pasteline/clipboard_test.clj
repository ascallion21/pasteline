(ns pasteline.clipboard-test
  (:require [clojure.test :refer :all]
            [pasteline.clipboard :refer :all]))

(deftest clipget-test
  (testing "Retrieving and setting valid clipboard data"
    (let [data "Test Value"]
      (pasteline.clipboard/clipset data)
      (is (= data (clipget))))))

(deftest clipget-test-null
  (testing "Retrieving and setting null clipboard data"
    (let [data nil]
      (pasteline.clipboard/clipset data)
      (is (= (str data) (clipget))))))

(deftest clipget-test-numeric
  (testing "Retrieving and setting numeric clipboard data"
    (let [data 123]
      (pasteline.clipboard/clipset data)
      (is (= (str data) (clipget))))))
