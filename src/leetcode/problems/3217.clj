(ns leetcode.problems.3217
  "You are given an array of integers nums and the head of a linked list. Return
  the head of the modified linked list after removing all nodes from the linked
  list that have a value that exists in nums.

  Constraints:

    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^5
    All elements in nums are unique.
    The number of nodes in the given list is in the range [1, 10^5].
    1 <= Node.val <= 10^5
    The input is generated such that there is at least one node in the linked list that has a value not present in nums.")

(defrecord ListNode [val next])

(defn make-list-node
  ([] (make-list-node 0))
  ([val] (->ListNode val nil))
  ([val next-node]
   (->ListNode val next-node)))

(defn linked-list-remove
  "Return `linked-list` without any nodes for which `(pred (:val ListNode))`
  returns logical false."
  [pred linked-list]
  (when linked-list
    (if-not (pred (:val linked-list))
      (make-list-node (:val linked-list)
                      (linked-list-remove pred (:next linked-list)))
      (linked-list-remove pred (:next linked-list)))))

(defn solution
  [nums-to-remove linked-list]
  (linked-list-remove (set nums-to-remove) linked-list))
