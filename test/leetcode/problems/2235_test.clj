(ns leetcode.problems.2235-test
  (:require [leetcode.problems.2235 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= 17 (sut/solution 12 5))))

(t/deftest case2
  (t/is (= -6 (sut/solution -10 4))))
