(ns leetcode.problems.876
  "Given the head of a singly linked list, return the middle node of the linked list.

  If there are two middle nodes, return the second middle node.

  Constraints:

    The number of nodes in the list is in the range [1, 100].
    1 <= Node.val <= 100")

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
  [linked-list])
