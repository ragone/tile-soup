(ns tile-soup.map
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.properties :as properties]
            [tile-soup.tileset :as tileset]
            [tile-soup.layer :as layer]
            [tile-soup.objectgroup :as objectgroup]
            [tile-soup.imagelayer :as imagelayer]
            [tile-soup.group :as group]))

(s/def ::version string?)
(s/def ::tiledversion string?)
(s/def ::orientation #{"orthogonal"
                       "isometric"
                       "staggered"
                       "hexagonal"})
(s/def ::renderorder #{"right-down"
                       "right-up"
                       "left-down"
                       "left-up"})
(s/def ::width u/str->int)
(s/def ::height u/str->int)
(s/def ::tilewidth u/str->int)
(s/def ::tileheight u/str->int)
(s/def ::hexsidelength u/str->int)
(s/def ::staggeraxis #{"x" "y"})
(s/def ::staggerindex #{"even" "odd"})
(s/def ::backgroundcolor string?)
(s/def ::nextlayerid u/str->int)
(s/def ::nextobjectid u/str->int)

(s/def ::attrs (s/keys
                 :opt-un [::version
                          ::tiledversion
                          ::orientation
                          ::renderorder
                          ::width
                          ::height
                          ::tilewidth
                          ::tileheight
                          ::hexsidelength
                          ::staggeraxis
                          ::staggerindex
                          ::backgroundcolor
                          ::nextlayerid
                          ::nextobjectid]))

(defmulti spec :tag)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :tileset [_] ::tileset/tileset)
(defmethod spec :layer [_] ::layer/layer)
(defmethod spec :objectgroup [_] ::objectgroup/objectgroup)
(defmethod spec :imagelayer [_] ::imagelayer/imagelayer)
(defmethod spec :group [_] ::group/group)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in map tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::map (s/keys :req-un [::attrs ::content]))

