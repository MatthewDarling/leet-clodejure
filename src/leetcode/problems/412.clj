(ns leetcode.problems.412
  "Given an integer n, return a string array answer (1-indexed) where:

    answer[i] == 'FizzBuzz' if i is divisible by 3 and 5.
    answer[i] == 'Fizz' if i is divisible by 3.
    answer[i] == 'Buzz' if i is divisible by 5.
    answer[i] == i (as a string) if none of the above conditions are true.

  Constraints:

    1 <= n <= 10^4")

(def compute-fns
  [str str (constantly "Fizz") str (constantly "Buzz")
   (constantly "Fizz") str str (constantly "Fizz") (constantly "Buzz")
   str (constantly "Fizz") str str (constantly "FizzBuzz")])

(def compute-cycle (cycle compute-fns))

(defn solution
  [num]
  (map #(%1 %2) compute-cycle (range 1 (inc num))))
