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

(defn seq->linked-list
  "Convert the provided `seq-to-link` into a linked list of `ListNode`s, in the
  same order as the original seq."
  [seq-to-link]
  (let [[reversed-head & reversed-tail] (reverse seq-to-link)]
    (reduce (fn [prev-node new-val]
              (make-list-node new-val prev-node))
            (make-list-node reversed-head)
            reversed-tail)))

(defn linked-list-remove
  "Return a vector of the `:val`s in `linked-list` for which
  `(pred (:val ListNode))` returns logical false."
  [pred linked-list]
  (loop [output-vals []
         remaining-list linked-list]
    (if-not remaining-list
      output-vals
      (if-not (pred (:val remaining-list))
        (recur (conj output-vals (:val remaining-list))
               (:next remaining-list))
        (recur output-vals (:next remaining-list))))))

(defn solution
  [nums-to-remove linked-list]
  (let [remove-set (set nums-to-remove)]
    (seq->linked-list (linked-list-remove remove-set linked-list))))
