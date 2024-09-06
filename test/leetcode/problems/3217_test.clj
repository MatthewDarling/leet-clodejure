(ns leetcode.problems.3217-test
  (:require [leetcode.problems.3217 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= (sut/make-list-node 4 (sut/make-list-node 5))
           (sut/solution [1 2 3]
                         (->> 5
                              sut/make-list-node
                              (sut/make-list-node 4)
                              (sut/make-list-node 3)
                              (sut/make-list-node 2)
                              (sut/make-list-node 1))))))

(t/deftest case2
  (t/is (= (->> 2 sut/make-list-node (sut/make-list-node 2) (sut/make-list-node 2))
           (sut/solution [1]
                         (->> 2
                              sut/make-list-node
                              (sut/make-list-node 1)
                              (sut/make-list-node 2)
                              (sut/make-list-node 1)
                              (sut/make-list-node 2)
                              (sut/make-list-node 1))))))

(t/deftest case3
  (t/is (= (->> 4
                sut/make-list-node
                (sut/make-list-node 3)
                (sut/make-list-node 2)
                (sut/make-list-node 1))
           (sut/solution [5]
                         (->> 4
                              sut/make-list-node
                              (sut/make-list-node 3)
                              (sut/make-list-node 2)
                              (sut/make-list-node 1))))))
