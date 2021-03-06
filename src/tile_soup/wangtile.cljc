(ns tile-soup.wangtile
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::tileid u/str->int)
(s/def ::wangid u/str->int)

(s/def ::attrs (s/keys
                 :req-un [::tileid
                          ::wangid]))

(s/def ::wangtile (s/keys :req-un [::attrs]))

