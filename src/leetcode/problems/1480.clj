(ns leetcode.problems.1480
  "Given an array nums. We define a running sum of an array
  as runningSum[i] = sum(nums[0]…nums[i]).

  Return the running sum of nums.

  Constraints:

    1 <= nums.length <= 1000
    -10^6 <= nums[i] <= 10^6")

(defn solution
  [nums]
  (reduce (fn [acc num]
            (->> num
                 (+ (or (last acc) 0))
                 (conj acc)))
          []
          nums))
