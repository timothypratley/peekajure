(ns peekajure.core)

(defn s-keys [x]
  (when (map? x)
    (str "{" (keys x) "}")))

(defn trunc [s n]
  (if (<= (count s) n)
    s
    (subs s 0 n)))

(defn trunc-keyword [k n]
  (let [s (pr-str k)]
    (if (<= (count s) n)
      s
      (let [s (trunc (name k) 20)]
        (str ":.../" s)))))

(defn s-first [x]
  ;; TODO: what about numbers?
  (if (keyword? x)
    (trunc-keyword x 20)
    (try
      (when-let [[begin head end]
                 (cond
                   (map? x) ["{" (first x) "}"]
                   (set? x) ["#{" (first x) "}"]
                   (vector? x) ["[" (first x) "]"]
                   (seq? x) ["(" (first x) ")"]
                   (string? x) [\" (trunc x 20) \"]
                   (.isArray (type x)) [(type x) (first x) "]"]
                   :else ["<" (first x) ">"])]
        (str begin head "..." (count x) end))
      (catch Throwable _ex))))
(comment
  (s-first :foobar))


(defn s-nested [x]
  (when (and (map? x)
             (< (count (keys x)) 5))
    (-> x
        (update-keys s-first)
        (update-vals s-first))))

(defn s-count [x]
  (try
    (when-let [[begin end] (cond
                             (map? x) ["{" "}"]
                             (set? x) ["#{" "}"]
                             (vector? x) ["[" "]"]
                             (seq? x) ["(" ")"]
                             (string? x) [\" \"]
                             (.isArray (type x)) [(type x) "]"]
                             :else ["<" ">"])]
      (str begin "..." (count x) end))
    (catch Throwable _ex)))

(defn s-type [x]
  (str (type x)))

(defn criteria [s]
  (and s (< (count s) 200)))

(def summarizers
  [pr-str
   s-nested
   s-first
   s-keys
   s-count
   s-type])

(defn summarize [x]
  (first (for [f summarizers
               :let [s (f x)]
               :when (criteria s)]
           s)))

(defn peeka [x]
  (println (summarize x)))

(comment
  (require '[clojure.string :as str])
  [(peeka "hello")
   (peeka (str/join (repeat 100 "hello")))
   (peeka {:a (str/join (repeat 100 "hello"))
           :b (str/join (repeat 100 "hello"))})
   (peeka (range 1000))
   (peeka (vec (range 1000)))
   (peeka (int-array (range 1000)))
   (peeka (zipmap (range 1000) (range 1000)))]

  )
