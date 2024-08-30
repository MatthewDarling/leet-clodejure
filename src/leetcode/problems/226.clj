(ns leetcode.problems.226
  "Given the root of a binary tree, invert the tree, and return its root.

  Constraints:

    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100")

;; their binary tree structure:
;; [     4,
;;    2,    7,
;;  1,3,    6,9]

(defn start-new-tree-layer?
  "Rather than operate on the flat vector input used by LeetCode, it's trivial to
  invert the tree if each layer of nodes is its own vector. Since we're working
  with a binary tree, we want to start a new layer of branches each time the
  current layer is double the size of the previous layer.

  Because the first layer has no preceding layer to compare against, it's always
  `true` that a new layer should be started for the second element onwards. For
  the second element onwards, return `true` if the last collection nested in
  `coll` is double the length of its preceding element."
  [coll]
  (or (= 1 (count coll))
      (= (* 2 (count (last (pop coll))))
         (count (last coll)))))

(defn solution
  [[root & children]]
  (let [layered (reduce (fn [acc item]
                          (if (start-new-tree-layer? acc)
                            (conj acc [item])
                            (conj (pop acc) (conj (last acc) item))))
                        (if root [[root]] [])
                        children)
        inverted-layers (map reverse layered)]
    (flatten inverted-layers)))
