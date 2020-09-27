(ns pasteline.robot
  (:import (java.awt Robot Robot)
           java.awt.event.KeyEvent))

(defn robot
  "Creates a new instance of Robot"
  []
  (Robot.))

(defn pastetext
  "Invokes ctrl-V"
  []
  (let [r (robot)]
    (.keyPress r KeyEvent/VK_CONTROL)
    (.keyPress r KeyEvent/VK_V)
    (.keyRelease r KeyEvent/VK_V)
    (.keyRelease r KeyEvent/VK_CONTROL)))