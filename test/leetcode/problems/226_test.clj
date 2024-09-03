(ns leetcode.problems.226-test
  (:require [leetcode.problems.226 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= [4,7,2,9,6,3,1]
           (sut/solution [4,2,7,1,3,6,9]))))

(t/deftest case2
  (t/is (= [2,3,1]
           (sut/solution [2,1,3]))))

(t/deftest case3
  (t/is (= [] (sut/solution []))))

(t/deftest my-cases
  (t/is (= [1] (sut/solution [1]))
        "No-op on single element collection")
  (t/is (= [1 3 2 4] (sut/solution [1 2 3 4]))
        "Handles an unbalanced tree")
  (t/is (= [1
            3,            2
            7,     6,     5,     4
            15 14, 13 12, 11 10, 9 8]
           (sut/solution (vec (range 1 16))))
        "Supports trees with more depth"))
