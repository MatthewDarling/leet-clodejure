(ns leetcode.problems.412-test
  (:require [leetcode.problems.412 :as sut]
            [clojure.test :as t]))

(t/deftest case1
  (t/is (= ["1" "2" "Fizz"]
           (sut/solution 3))))

(t/deftest case2
  (t/is (= ["1" "2" "Fizz" "4" "Buzz"]
           (sut/solution 5))))

(t/deftest case3
  (t/is (= ["1" "2" "Fizz" "4" "Buzz" "Fizz"
            "7" "8" "Fizz" "Buzz" "11" "Fizz"
            "13" "14" "FizzBuzz"]
           (sut/solution 15))))
