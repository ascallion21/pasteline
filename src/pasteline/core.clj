(ns pasteline.core
  (:gen-class)
  (:require [clojure.string :as s]
            [pasteline.clipboard :as clipboard]
            [pasteline.robot :as robot]
            [keymaster.core]))

(defn onPaste
  "1. Grabs the current text from the clipboard
   2. Replaces all occurences of newlines with {replacementText}
   3. Sets the modified text onto the clipboard
   4. Invokes the ctrl-V paste command"
  [& {:keys [replacementText] :or {replacementText ""}}]
  (if-let [clipboardText (clipboard/clipget)]
    (let [formattedText (s/replace clipboardText #"[\r\n]+", replacementText)]
      (clipboard/clipset formattedText)
      (robot/pastetext))
    (println "There is nothing to paste")))

(defn -main
  "Strips newlines from a text block when pasting via ctrl-alt-v
  TODO - alter this to accept a custom value for replacementText or the keybinding"
  [& args]
  (let [provider (keymaster.core/make-provider)]
    (keymaster.core/register provider "control alt V" #(onPaste))))
