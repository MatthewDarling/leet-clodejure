Manually translating LeetCode problems and test cases to Clojure. I wish they had something sort of like [Exercism](https://exercism.org/)'s CLI, which might make it easier to be language agnostic.

If you could import external packages, we could load Clojure as a `.jar` file and submit as Java. But a bit of Googling suggests you only get the standard library, which makes perfect sense for sandboxing purposes. So unless there's a very clever solution to turn an entire `.jar` file into a magic blob of code you can load in an arbitrary Java file, that seems like a dead end for now.

They do support Racket, and I know there are a few `#lang` options that are Clojure-ish, but I don't know enough Racket to pursue that for now.

Anyway! No promises how far I'll get, just going to take it one problem at a time.

# Repository structure

A single `deps.edn` project with `src` and `test` files numbered as on LeetCode. The `main` branch will hold only stubs enabling you to solve the problem yourself, while the `solutions` branch will have my solutions. I don't find that non-`main` branches show up in Google results, so I'm hopeful that will maintain some amount of competitive integrity.
