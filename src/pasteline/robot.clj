(ns pasteline.robot)
  
(import '[java.awt Robot Robot])
(import '[java.awt.event KeyEvent])

(defn robot []
  (Robot.)
)

(defn pastetext []
  (let [r (robot)] (try
    (.keyPress r KeyEvent/VK_CONTROL)
    (.keyPress r KeyEvent/VK_V)
    (.keyRelease r KeyEvent/VK_V)
    (.keyRelease r KeyEvent/VK_CONTROL)
    (catch java.lang.NullPointerException e nil))))
