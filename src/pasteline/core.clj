(ns pasteline.core
  (:require [clojure.string :as s]
            [pasteline.clipboard :as clipboard]
            [pasteline.robot :as robot]
            [keymaster.core]))

(defn onPaste
  "1. Grabs the current text from the clipboard
   2. Replaces all occurences of newlines with {replacementText}
   3. Sets the modified text onto the clipboard
   4. Invokes the ctrl-V paste command"
  [replacementRegex, replacementText]
  (if-let [clipboardText (clipboard/clipget)]
    (let [formattedText (s/replace clipboardText replacementRegex, replacementText)]
      (clipboard/clipset formattedText)
      (robot/pastetext))
    (println "There is nothing to paste")))

(defn -main
  "Strips newlines from a text block when pasting via ctrl-alt-v"
  [& args]
  (let [provider (keymaster.core/make-provider)
        keybinding (nth args 0 "control alt V")
        replacementRegex (re-pattern (nth args 1 "[\r\n]+"))
        replacementText (nth args 2 "")]
    (keymaster.core/register provider keybinding #(onPaste replacementRegex replacementText))))
