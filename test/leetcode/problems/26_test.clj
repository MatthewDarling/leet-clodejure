(ns leetcode.problems.26-test
  (:require [leetcode.problems.26 :as sut]
            [clojure.test :as t]))

(defn array-starts-with
  "Return `true` if `arr` starts with the same elements as `subarr`. Doesn't care
  about elements of `arr` past the length of `subarr`."
  [arr subarr]
  (every?
   true?
   (for [n (range 0 (alength subarr))]
     (when (< n (alength arr))
       (= (aget arr n) (aget subarr n))))))

(t/deftest case1
  (let [input (int-array [1 1 2])]
    (t/is (and (= 2 (sut/solution input))
               (array-starts-with input
                                  (int-array [1 2]))))))

(t/deftest case2
  (let [input (int-array [0 0 1 1 1 2 2 3 3 4])]
    (t/is (and (= 5 (sut/solution input))
               (array-starts-with input
                                  (int-array [0 1 2 3 4]))))))

(t/deftest my-cases
  (t/is (array-starts-with (int-array [1 2 3])
                           (int-array [1 2]))
        "Handles subarr being shorter than arr")
  (t/is (array-starts-with (int-array [1 2 3])
                           (int-array [1 2 3]))
        "Handles subarry being entirely equal to arr")
  (t/is (false? (array-starts-with (int-array [1 2 3])
                                   (int-array [1 2 3 4])))
        "Handles subarray being longer than arr")
  (t/is (false? (array-starts-with (int-array [1 2 3])
                                   (int-array [4 5 6])))
        "Handles subarray having no matching elements")
  (t/is (false? (array-starts-with (int-array [1 2 3])
                                   (int-array [1 2 4])))
        "Handles subarray having some, but not all, matching elements"))
