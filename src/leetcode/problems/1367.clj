(ns leetcode.problems.1367
  "Given a binary tree root and a linked list with head as the first node.

  Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

  In this context downward path means a path that starts at some node and goes downwards.

  Constraints:

    The number of nodes in the tree will be in the range [1, 2500].
    The number of nodes in the list will be in the range [1, 100].
    1 <= Node.val <= 100 for each node in the linked list and binary tree.")

(defrecord ListNode [val next])

(defrecord TreeNode [val left right])

(defn make-list-node
  ([] (make-list-node 0))
  ([val] (->ListNode val nil))
  ([val next-node]
   (->ListNode val next-node)))

(defn make-tree-node
  ([]
   (make-tree-node 0))
  ([val]
   (make-tree-node val nil nil))
  ([val left]
   (make-tree-node val left nil))
  ([val left right]
   (->TreeNode val left right)))

(defn solution
  [linked-list binary-tree])
