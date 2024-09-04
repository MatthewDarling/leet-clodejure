(ns leetcode.problems.1480-test
  (:require [leetcode.problems.1480 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= [1 3 6 10]
           (sut/solution [1 2 3 4]))))

(t/deftest case2
  (t/is (= [1 2 3 4 5]
           (sut/solution [1 1 1 1 1]))))

(t/deftest case3
  (t/is (= [3 4 6 16 17]
           (sut/solution [3 1 2 10 1]))))
