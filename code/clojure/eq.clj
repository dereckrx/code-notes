; run with: $ clojure eq.clj

(defn eq [a b] (if (= a b) (println ".") (println (str "Fail: " a " == " b))))

(eq 1 1)
(eq 1 2)
(eq 1.0 1.0)
(eq "hello" "hello")
(eq [1 2 3] [1 2 3])
(eq {:a 1, :b [1, 2]}, {:a 1, :b [1, 2]})
(eq {:a 1, :b [1, 2]}, {:a 1, :b [1, 2, 3]})
