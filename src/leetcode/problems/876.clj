(ns leetcode.problems.876
  "Given the head of a singly linked list, return the middle node of the linked list.

  If there are two middle nodes, return the second middle node.

  Constraints:

    The number of nodes in the list is in the range [1, 100].
    1 <= Node.val <= 100"
  (:require [clojure.math :as math]))

(defrecord ListNode [val next])

(defn make-list-node
  ([] (make-list-node 0))
  ([val] (->ListNode val nil))
  ([val next-node]
   (->ListNode val next-node)))

(defn seq->linked-list
  "Convert the provided `seq-to-link` into a linked list of `ListNode`s, in the
  same order as the original seq."
  [seq-to-link]
  (let [[reversed-head & reversed-tail] (reverse seq-to-link)]
    (reduce (fn [prev-node new-val]
              (make-list-node new-val prev-node))
            (make-list-node reversed-head)
            reversed-tail)))

(defn solution
  [linked-list]
  (let [unlinked (reduce conj
                         []
                         (->> linked-list
                              (iterate :next)
                              (take-while some?)))
        half-count (-> unlinked
                       count
                       (/ 2)
                       (math/round))
        desired-middle (if (odd? (count unlinked))
                         (dec half-count)
                         half-count)]
    (nth unlinked desired-middle)))

(defn pointer-ish-solution
  "Mimic the looping, pointer following approach that's traditionally used in
  imperative languages for this problem. You have a 'slow pointer' that advances
  one element at a time, and a 'fast pointer' that advances by two elements. The
  loop is initialized with the slow pointer, aka `middle`, at the first element
  and the fast pointer, aka `end`, at the second element."
  [linked-list]
  (loop [middle linked-list
         end (:next linked-list)]
    (if end
      (recur (:next middle)
             (some-> end :next :next))
      middle)))
