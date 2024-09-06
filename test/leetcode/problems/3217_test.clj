(ns leetcode.problems.3217-test
  (:require [leetcode.problems.3217 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= (sut/seq->linked-list [4 5])
           (sut/solution [1 2 3]
                         (sut/seq->linked-list (range 1 6))))))

(t/deftest case2
  (t/is (= (sut/seq->linked-list [2 2 2])
           (sut/solution [1]
                         (sut/seq->linked-list [1 2 1 2 1 2])))))

(t/deftest case3
  (t/is (= (sut/seq->linked-list (range 1 5))
           (sut/solution [5]
                         (sut/seq->linked-list (range 1 5))))))

(t/deftest my-cases
  (t/is (= (->> 6
                sut/make-list-node
                (sut/make-list-node 5)
                (sut/make-list-node 4)
                (sut/make-list-node 3)
                (sut/make-list-node 2)
                (sut/make-list-node 1))
           (sut/seq->linked-list (range 1 7)))
        "Helper function builds the same lists as a manual approach"))
