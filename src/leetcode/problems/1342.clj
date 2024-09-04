(ns leetcode.problems.1342
  "Given an integer num, return the number of steps to reduce it to zero.

  In one step, if the current number is even, you have to divide it by 2,
  otherwise, you have to subtract 1 from it.

  Constraints:

    0 <= num <= 10^6")

(defn solution
  [num]
  (loop [step-count 0
         current-num num]
    (if (zero? current-num)
      step-count
      (recur (inc step-count)
             (if (even? current-num)
               (/ current-num 2)
               (dec current-num))))))
