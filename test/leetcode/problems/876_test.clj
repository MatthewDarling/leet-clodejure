(ns leetcode.problems.876-test
  (:require [leetcode.problems.876 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= (sut/seq->linked-list [3 4 5])
           (sut/solution (sut/seq->linked-list (range 1 6))))))

(t/deftest case2
  (t/is (= (sut/seq->linked-list [4 5 6])
           (sut/solution (sut/seq->linked-list (range 1 7))))))

(t/deftest my-cases
  (t/is (= (->> 6
                sut/make-list-node
                (sut/make-list-node 5)
                (sut/make-list-node 4)
                (sut/make-list-node 3)
                (sut/make-list-node 2)
                (sut/make-list-node 1))
           (sut/seq->linked-list (range 1 7)))
        "Helper function builds the same lists as a manual approach")
  (t/is (= (sut/make-list-node 1)
           (sut/solution (sut/seq->linked-list (range 1 2))))
        "A list with only one element returns that as the middle")
  (t/is (= (sut/make-list-node 2)
           (sut/solution (sut/seq->linked-list (range 1 3))))
        "A list with two elements returns the last element")
  (let [test-input (sut/seq->linked-list (range 1 6))]
      (t/is (= (sut/solution test-input)
               (sut/pointer-ish-solution test-input))
            "Both solutions return the same result for odd-length lists"))
  (let [test-input (sut/seq->linked-list (range 1 7))]
      (t/is (= (sut/solution test-input)
               (sut/pointer-ish-solution test-input))
            "Both solutions return the same result for even-length lists")))
