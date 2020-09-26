(ns pasteline.core
  (:gen-class))

(require 'pasteline.clipboard)
(require 'pasteline.robot)
(require 'keymaster.core)

(defn paste []
  ((let [text (clojure.string/replace (pasteline.clipboard/clipget) #"[\r\n]+", "")]
     (pasteline.clipboard/clipset text)
     (pasteline.robot/pastetext))))

(defn -main
  "Strips newlines from a text block when pasting via ctrl-alt-v"
  [& args]
  (let [provider (keymaster.core/make-provider)]
    (keymaster.core/register provider "control alt V" #((pasteline.core/paste)))))
