(ns leetcode.problems.1367
  "Given a binary tree root and a linked list with head as the first node.

  Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.

  In this context downward path means a path that starts at some node and goes downwards.

  Constraints:

    The number of nodes in the tree will be in the range [1, 2500].
    The number of nodes in the list will be in the range [1, 100].
    1 <= Node.val <= 100 for each node in the linked list and binary tree."
  (:require [clojure.zip :as zip]))

(defrecord ListNode [val next])

(defrecord TreeNode [val left right])

(defn make-list-node
  ([] (make-list-node 0))
  ([val] (->ListNode val nil))
  ([val next-node]
   (->ListNode val next-node)))

(defmethod print-method TreeNode
  [node ^java.io.Writer w]
  (.write w (format "node: %s [%s, %s]"
                    (:val node)
                    ;; Using pr-str allows the print method to recur with the child nodes
                    (pr-str (:left node))
                    (pr-str (:right node)))))

(defn tree-node?
  [obj]
  (instance? TreeNode obj))

(defn make-tree-node
  "Converts the input, and any provided children, into `TreeNode`s."
  ([]
   (make-tree-node 0))
  ([val]
   (cond
     (tree-node? val) val
     (nil? val) (->TreeNode nil nil nil)
     :else (make-tree-node val nil nil)))
  ([val left]
   (make-tree-node val left nil))
  ([val left right]
   (->TreeNode val
               (make-tree-node left)
               (make-tree-node right))))

(def child-seq (juxt :left :right))

(defn children
  [node]
  (child-seq node))

(defn branch?
  "A node is a branch if it has more than 0 non-`nil` children."
  [node]
  (and (:val node)
       (->> node
            children
            (remove (some-fn nil? (comp nil? :val)))
            count
            pos?)))

(defn make-node
  "`clojure.zip` takes a function to 'make new nodes,' but it's also used when
  navigating the tree to resolve changed nodes. So this may be called with just
  the `val` desired for a new `TreeNode`, or to update the children of an
  existing `TreeNode`. Therefore, we'll use `make-tree-node` to coerce
  `val-or-node`, and manually set the `left` and `right` nodes."
  [val-or-node [left right]]
  (let [as-tree-node (make-tree-node val-or-node)]
    (assoc as-tree-node
           :left (make-tree-node left)
           :right (make-tree-node right))))

(defn zip-wrapper
  "A helper function to provide the right functional arguments to
  `clojure.zip/zipper` for a `TreeNode` tree."
  [root]
  (zip/zipper branch?
              children
              make-node
              (make-tree-node root)))

;;; clojure.zip helper functions
(defn top-of-tree
  "The built-in `clojure.zip/root` function has this same logic, but also calls
  `clojure.zip/node` at the end. So to navigate to the top of the tree, we need
  our own function."
  [loc]
  (if-let [parent (zip/up loc)]
    (recur parent)
    loc))

(defn child-locs
  "The built-in `clojure.zip/children` function operates on a node, rather than a
  `loc`. You can't really reconstruct the navigation data from just the node, so
  if you want to process the children as `loc`s, you need this function
  instead."
  [loc]
  (when-let [first-child (zip/down loc)]
    (->> first-child
         (iterate zip/right)
         (take-while some?))))

;;; Solution implementation
(defn breadth-first-filter
  "Like `filter`, but using a breadth-first search of the provided `zipper`.

  `filter-loc` must be a function that receives a `clojure.zip` `loc` structure,
  and returns a truthy value if you want to keep that `loc` in the output. A
  `loc` is a vector with mandatory metadata and two elements: the `node`, and
  the current zipper navigation data. You can filter on the node itself, and/or
  the navigation data."
  [zipper filter-loc]
  (loop [result []
         remaining-queue (conj clojure.lang.PersistentQueue/EMPTY zipper)]
    (if (seq remaining-queue)
      (let [current-loc (peek remaining-queue)
            child-nodes (child-locs current-loc)]
        (recur (if (filter-loc current-loc)
                 (conj result current-loc)
                 result)
               (into (pop remaining-queue) child-nodes)))
      result)))

(defn seq->unbalanced-tree
  [[root & children]]
  (let [tree-initial (-> (zip-wrapper root)
                         (zip/edit make-node (take 2 children)))]
    (loop [tree-so-far tree-initial
           remaining-children (drop 2 children)]
      (if-not (seq remaining-children)
        (top-of-tree tree-so-far)
        (recur (-> tree-so-far
                   top-of-tree
                   (breadth-first-filter (every-pred (comp some? :val zip/node)
                                                     (comp not zip/branch?)))
                   first
                   (zip/edit make-node (take 2 remaining-children)))
               (drop 2 remaining-children))))))



(comment
  (= (zip/root (seq->unbalanced-tree [2 nil 2 nil 2 nil 1]))
     (make-tree-node 2
                     nil
                     (make-tree-node 2
                                     nil
                                     (make-tree-node 2
                                                     nil
                                                     (make-tree-node 1))))))

(defn solution
  [linked-list binary-tree])
