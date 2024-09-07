(ns leetcode.problems.1367-test
  (:require [leetcode.problems.1367 :as sut]
            [clojure.test :as t]))

(def example-tree
  (sut/make-tree-node 1
                      (sut/make-tree-node 4
                                          (sut/make-tree-node 2
                                                              (sut/make-tree-node 1)))
                      (sut/make-tree-node 4
                                          (sut/make-tree-node 2
                                                              (sut/make-tree-node 6)
                                                              (sut/make-tree-node 8
                                                                                  (sut/make-tree-node 1)
                                                                                  (sut/make-tree-node 3))))))

(t/deftest case1
  (t/is (sut/solution (->> 8
                           sut/make-list-node
                           (sut/make-list-node 2)
                           (sut/make-list-node 4))
                      example-tree)))

(t/deftest case2
  (t/is (sut/solution (->> 8
                           sut/make-list-node
                           (sut/make-list-node 6)
                           (sut/make-list-node 2)
                           (sut/make-list-node 4)
                           (sut/make-list-node 1))
                      example-tree)))
