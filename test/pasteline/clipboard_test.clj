(ns pasteline.clipboard-test
  (:require [clojure.test :refer :all]
            [pasteline.clipboard :as clipboard]))

(deftest clipget-test
  (testing "Retrieving and setting valid clipboard data"
    (let [data "Test Value"]
      (clipboard/clipset data)
      (is (= data (clipboard/clipget))))))

(deftest clipget-test-null
  (testing "Retrieving and setting null clipboard data"
    (let [data nil]
      (clipboard/clipset data)
      (is (= (str data) (clipboard/clipget))))))

(deftest clipget-test-numeric
  (testing "Retrieving and setting numeric clipboard data"
    (let [data 123]
      (clipboard/clipset data)
      (is (= (str data) (clipboard/clipget))))))
