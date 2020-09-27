(ns pasteline.clipboard
  (:import [java.awt.datatransfer DataFlavor StringSelection UnsupportedFlavorException]))

(defn clipboard
  "Provides an instance of the system clipboard"
  []
  (.getSystemClipboard (java.awt.Toolkit/getDefaultToolkit)))

(defn clipget
  "Retrieves the current text in the clipboard
   Returns null if nothing has been copied previously"
  []
  (try
    (.getTransferData (.getContents (clipboard) nil) (DataFlavor/stringFlavor))
    (catch java.lang.NullPointerException _ nil)
    (catch UnsupportedFlavorException _ nil)))

(defn clipset
  "Sets the current clipboard text"
  [text]
  (let [selection (StringSelection. (str text))]
    (.setContents (clipboard) selection selection)))
