(ns leetcode.problems.383-test
  (:require [leetcode.problems.383 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (false? (sut/solution "a" "b"))))

(t/deftest case2
  (t/is (false? (sut/solution "aa" "ab"))))

(t/deftest case3
  (t/is (sut/solution "aa" "aab")))
