(ns leetcode.problems.1367-test
  (:require [leetcode.problems.1367 :as sut]
            [clojure.pprint :as pprint]
            [clojure.test :as t]
            [clojure.zip :as zip]))

(defn seq->linked-list
  "Convert the provided `seq-to-link` into a linked list of `ListNode`s, in the
  same order as the original seq."
  [seq-to-link]
  (let [[reversed-head & reversed-tail] (reverse seq-to-link)]
    (reduce (fn [prev-node new-val]
              (sut/make-list-node new-val prev-node))
            (sut/make-list-node reversed-head)
            reversed-tail)))

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
  (t/is (sut/solution (seq->linked-list [4 2 8])
                      example-tree)))

(t/deftest case2
  (t/is (sut/solution (seq->linked-list [1 4 2 6 8])
                      example-tree)))

(t/deftest from-comments
  (t/is (sut/solution (seq->linked-list [1 10])
                      (zip/root (sut/seq->unbalanced-tree [1 nil 1 10 1 9]))))
  (t/is (sut/solution (seq->linked-list [2 2 1])
                      (zip/root (sut/seq->unbalanced-tree [2 nil 2 nil 2 nil 1])))))


(t/deftest my-cases
  (t/is (= (->> 6
                sut/make-list-node
                (sut/make-list-node 5)
                (sut/make-list-node 4)
                (sut/make-list-node 3)
                (sut/make-list-node 2)
                (sut/make-list-node 1))
           (seq->linked-list (range 1 7)))
        "Linked list helper function builds the same lists as a manual approach")
  (t/is (= (zip/root (sut/seq->unbalanced-tree [2 nil 2 nil 2 nil 1]))
           (sut/make-tree-node 2
                               nil
                               (sut/make-tree-node 2
                                                   nil
                                                   (sut/make-tree-node 2
                                                                       nil
                                                                       (sut/make-tree-node 1)))))
        "Unbalanced tree helper function builds the same tree as a manual approach")
  (t/is (= (sut/make-tree-node 1
                               nil
                               (sut/make-tree-node 1
                                                   (sut/make-tree-node 10 (sut/make-tree-node 9))
                                                   (sut/make-tree-node 1)))
           (zip/root (sut/seq->unbalanced-tree [1 nil 1 10 1 9])))
        "Builds same tree")
  (t/is (= (sut/make-tree-node 1
                      (sut/make-tree-node 4
                                          (sut/make-tree-node 2
                                                              (sut/make-tree-node 1)))
                      (sut/make-tree-node 4
                                          (sut/make-tree-node 2
                                                              (sut/make-tree-node 6)
                                                              (sut/make-tree-node 8
                                                                                  (sut/make-tree-node 1)
                                                                                  (sut/make-tree-node 3)))))
           (zip/root (sut/seq->unbalanced-tree [1
                                                4     4
                                                2 nil 2 nil
                                                1 nil   6       8
                                                nil nil nil nil 1 3])))
        "Builds same tree as their example"))

(def expected {:val 1,
               :left
               {:val 4,
                :left
                {:val 2,
                 :left
                 {:val 1,
                  :left {:val nil, :left nil, :right nil},
                  :right {:val nil, :left nil, :right nil}},
                 :right {:val nil, :left nil, :right nil}},
                :right {:val nil, :left nil, :right nil}},
               :right
               {:val 4,
                :left
                {:val 2,
                 :left
                 {:val 6,
                  :left {:val nil, :left nil, :right nil},
                  :right {:val nil, :left nil, :right nil}},
                 :right
                 {:val 8,
                  :left
                  {:val 1,
                   :left {:val nil, :left nil, :right nil},
                   :right {:val nil, :left nil, :right nil}},
                  :right
                  {:val 3,
                   :left {:val nil, :left nil, :right nil},
                   :right {:val nil, :left nil, :right nil}}}},
                :right {:val nil, :left nil, :right nil}}})

(def actual   {:val 1,
               :left
               {:val 4,
                :left
                {:val 2,
                 :left
                 {:val 1,
                  :left
                  {:val 1,
                   :left {:val nil, :left nil, :right nil},
                   :right {:val nil, :left nil, :right nil}},
                  :right
                  {:val 3,
                   :left {:val nil, :left nil, :right nil},
                   :right {:val nil, :left nil, :right nil}}},
                 :right {:val nil, :left nil, :right nil}},
                :right {:val nil, :left nil, :right nil}},
               :right
               {:val 4,
                :left
                {:val 2,
                 :left
                 {:val 6,
                  :left {:val nil, :left nil, :right nil},
                  :right {:val nil, :left nil, :right nil}},
                 :right
                 {:val 8,
                  :left {:val nil, :left nil, :right nil},
                  :right {:val nil, :left nil, :right nil}}},
                :right {:val nil, :left nil, :right nil}}})
