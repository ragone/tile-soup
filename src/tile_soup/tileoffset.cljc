(ns tile-soup.tileoffset
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::x u/str->int)
(s/def ::y u/str->int)

(s/def ::attrs (s/keys :req-un [::x ::y]))

(s/def ::tileoffset (s/keys :req-un [::attrs]))

