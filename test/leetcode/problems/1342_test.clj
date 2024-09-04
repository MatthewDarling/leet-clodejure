(ns leetcode.problems.1342-test
  (:require [leetcode.problems.1342 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= 6 (sut/solution 14))))

(t/deftest case2
  (t/is (= 4 (sut/solution 8))))

(t/deftest case3
  (t/is (= 12 (sut/solution 123))))
