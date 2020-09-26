(ns pasteline.clipboard)

(import '[java.awt.datatransfer DataFlavor StringSelection UnsupportedFlavorException])

(defn clipboard []
  (.getSystemClipboard (java.awt.Toolkit/getDefaultToolkit)))

(defn clipget []
  (try
    (.getTransferData (.getContents (clipboard) nil) (DataFlavor/stringFlavor))
    (catch java.lang.NullPointerException e nil)
    (catch UnsupportedFlavorException e nil)))

(defn clipset [text]
  (let [selection (StringSelection. (str text))]
    (.setContents (clipboard) selection selection)))
