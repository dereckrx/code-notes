; clojure hello.clj
; IntelliJ Setup:
; * Requires 'clojure-1.9.0.jar' in the try_clojure/lib directory

(defn eq [a b] (if (= a b) (println ".") (println (str "Fail: " a " == " b))))

(def x 7)
(eq x 7)



;; Equivalent to: (fn [x] (+ 6 x))
(eq
  (#(+ %1 %2) 1 2)
  ((fn [x y] (+ x y)) 1 2))

;; Equivalent to: (fn [x y] (+ x y))
(eq
  (#(+ 1 %) 2)
  ((fn [x] (+ 1 x)) 2))

 ;; Equivalent to: (fn [x y & zs] (println x y zs))
 ;#(println %1 %2 %&)
