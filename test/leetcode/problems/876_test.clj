(ns leetcode.problems.876-test
  (:require [leetcode.problems.876 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= (->> 5
                sut/make-list-node
                (sut/make-list-node 4)
                (sut/make-list-node 3))
           (sut/solution (->> 5
                              sut/make-list-node
                              (sut/make-list-node 4)
                              (sut/make-list-node 3)
                              (sut/make-list-node 2)
                              (sut/make-list-node 1))))))

(t/deftest case2
  (t/is (= (->> 6
                sut/make-list-node
                (sut/make-list-node 5)
                (sut/make-list-node 4))
           (sut/solution (->> 6
                              sut/make-list-node
                              (sut/make-list-node 5)
                              (sut/make-list-node 4)
                              (sut/make-list-node 3)
                              (sut/make-list-node 2)
                              (sut/make-list-node 1))))))
