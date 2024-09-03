(ns leetcode.problems.invert-tree-zipper
  "Given the root of a binary tree, invert the tree, and return its root.

  Constraints:

    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100"
  (:require [clojure.zip :as zip]))

;;; Data structure and related functions
(defrecord TreeNode [val left right])

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
  "Converts the input, and any provided children, into `TreeNode`s. Will not create
  `TreeNode`s with `nil` for `val`."
  ([]
   (make-tree-node 0))
  ([val]
   (if (or (tree-node? val) (nil? val))
     val
     (make-tree-node val nil nil)))
  ([val left]
   (make-tree-node val left nil))
  ([val left right]
   (->TreeNode val
               (make-tree-node left)
               (make-tree-node right))))

(def child-seq (juxt :left :right))

(defn children
  [node]
  (->> node
       child-seq
       (remove nil?)))

(defn branch?
  "A node is a branch if it has more than 0 non-`nil` children."
  [node]
  (->> node
       children
       count
       pos?))

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

(defn insert-or-append-child
  "Like `clojure.zip/append-child`, but won't throw an exception if called on a leaf node.

  I find the existing behaviour, that you can only append if there are existing
  children, not very useful for programmatically building a tree. Admittedly,
  that's tied to my definition of `branch?`, which answers 'is this currently a
  branch?' while `clojure/zip` apparently thinks it means '*can this* be a
  branch?'. What I have now works better for everything except `append-child`,
  though, so here's a helper function."
  [loc item]
  (if (zip/branch? loc)
    (zip/append-child loc item)
    (zip/edit loc make-node [item])))

;;; Solution implementation
(defn breadth-first-transform
  "Breadth-first searches a given `zipper`, and if `transform-loc` is provided,
  will transform the tree top-down as the BFS progresses.

  `transform-loc` must be a function that receives and returns a `clojure.zip`
  `loc` structure. A `loc` is a vector with mandatory metadata and two elements:
  the `node`, and the current zipper navigation data. If you forget to include
  the metadata in your output, you'll get some interesting errors, and it won't
  be obvious why."
  ([zipper]
   (breadth-first-transform zipper identity))
  ([zipper transform-loc]
   (loop [result []
          remaining-queue (conj clojure.lang.PersistentQueue/EMPTY zipper)]
     (if (seq remaining-queue)
       (let [current-loc (transform-loc (peek remaining-queue))
             child-nodes (child-locs current-loc)]
         (recur (conj result current-loc)
                (into (pop remaining-queue) child-nodes)))
       result))))

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

(defn has-two-children?
  [loc]
  (when (and loc (zip/branch? loc))
    (some-> loc zip/children count (= 2))))

(defn next-unbalanced-node
  "Using a breadth-first search from the top of `zipper`, identify the next node
  that doesn't have two children. In effect, this identifies the next insertion
  point for building a balanced binary tree."
  [zipper]
  (-> zipper
      top-of-tree
      (breadth-first-filter (comp not has-two-children?))
      first))

(defn swap-children
  "Given a `TreeNode`, swap its `left` and `right` children for purposes of
  inverting the tree."
  [node]
  (let [[left right] (children node)]
    (assoc node
           :left right
           :right left)))

(defn invert-loc
  "Each element of a `clojure.zip` zipper is called a `loc`, and it is a vector
  with mandatory metadata and two elements: the `node`, and the current zipper
  navigation data.

  For this problem, we don't need to change the metadata or navigation data,
  only the node itself. So we copy the existing `nav-data` and metadata, while
  applying `swap-children` to each `node` as we search the tree."
  [[node nav-data :as loc]]
  (with-meta [(swap-children node) nav-data]
    (meta loc)))

;; their binary tree structure:
;; [     4,
;;    2,    7,
;;  1,3,    6,9]

(defn seq->tree
  [[root & children]]
  (loop [tree-so-far (zip-wrapper root)
         remaining-children children]
    (if-not (seq remaining-children)
      (top-of-tree tree-so-far)
      (recur (-> tree-so-far
                 next-unbalanced-node
                 (insert-or-append-child (first remaining-children)))
             (rest remaining-children)))))

(defn solution
  [tree-seq]
  (let [tree (seq->tree tree-seq)]
    (map (comp :val zip/node)
         (breadth-first-transform tree invert-loc))))

(comment
  (solution [4 2 7 1 3 6 9]))
