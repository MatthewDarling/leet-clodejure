(ns leetcode.problems.26
  "Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

  Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

    Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
    Return k.

  Constraints:

    1 <= nums.length <= 3 * 10^4
    -100 <= nums[i] <= 100
    nums is sorted in non-decreasing order.")

(defn solution
  "Edit the input array, `nums-array`, to overwrite consecutive duplicate values.
  Returns the count of unique values.

  A number of junk values will persist at the end of `nums-array` equal
  to `(- (alength nums-array) counter)`."
  [nums-array]
  (loop [counter 1
         idx 1]
    (if (> (alength nums-array) idx)
      (if (= (aget nums-array (dec idx))
             (aget nums-array idx))
        (recur counter (inc idx))
        (do
          (aset nums-array counter (aget nums-array idx))
          (recur (inc counter) (inc idx))))
      counter)))
