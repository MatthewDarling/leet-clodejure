(ns leetcode.problems.2236
  "You are given the root of a binary tree that consists of exactly 3 nodes: the root, its left child, and its right child.

  Return true if the value of the root is equal to the sum of the values of its two children, or false otherwise.

  Constraints:

    The tree consists only of the root, its left child, and its right child.
    -100 <= Node.val <= 100")

(defrecord TreeNode [val left right])

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
  [tree-seq]
  {:pre  [(= 3 (count tree-seq))
          (every? int? tree-seq)]}
  )
