(ns leetcode.problems.1672-test
  (:require [leetcode.problems.1672 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= 6 (sut/solution [[1 2 3]
                            [3 2 1]]))))

(t/deftest case2
  (t/is (= 10
           (sut/solution [[1 5]
                          [7 3]
                          [3 5]]))))

(t/deftest case3
  (t/is (= 17
           (sut/solution [[2 8 7]
                          [7 1 3]
                          [1 9 5]]))))
