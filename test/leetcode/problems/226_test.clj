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
