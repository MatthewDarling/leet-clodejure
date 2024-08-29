(ns leetcode.problems.226
  "Given the root of a binary tree, invert the tree, and return its root.

  Constraints:

    The number of nodes in the tree is in the range [0, 100].
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
  )
