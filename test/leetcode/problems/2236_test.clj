(ns leetcode.problems.2236-test
  (:require [leetcode.problems.2236 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (true? (sut/solution [10,4,6]))))

(t/deftest case2
  (t/is (false? (sut/solution [5,3,1]))))
