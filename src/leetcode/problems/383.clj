(ns leetcode.problems.383
  "Given two strings ransomNote and magazine, return true if ransomNote can be
  constructed by using the letters from magazine and false otherwise.

  Each letter in magazine can only be used once in ransomNote.

  Constraints:

    1 <= ransomNote.length, magazine.length <= 10^5
    ransomNote and magazine consist of lowercase English letters.")

(defn merge-only-matching-keys-with
  "Like `merge-with`, but only keeps keys present in `map1`."
  [f map1 map2]
  (merge-with f map1 (select-keys map2 (keys map1))))

(defn solution
  "Returns `true` if all letters of `ransom-note` are present in `magazine`.
  Determined by subtracting the count of each letter in the respective strings."
  [ransom-note magazine]
  (let [ransom-freqs (frequencies ransom-note)
        magazine-freqs (frequencies magazine)
        missing-letters (merge-only-matching-keys-with - ransom-freqs magazine-freqs)]
    (->> missing-letters vals (every? #(<= % 0)))))
