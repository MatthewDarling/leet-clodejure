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

(defn solution
  [linked-list])
