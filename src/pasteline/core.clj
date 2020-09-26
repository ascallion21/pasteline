(ns pasteline.core
  (:gen-class))

(require 'pasteline.clipboard)
(require 'pasteline.robot)
(require 'keymaster.core)
(import java.util.concurrent.locks.ReentrantLock)

(defn paste []
  ((let [text (clojure.string/replace (pasteline.clipboard/clipget) #"[\r\n]+", "")]
     (pasteline.clipboard/clipset text)
     (pasteline.robot/pastetext))))

(defn -main
  "Strips newlines from a text block when pasting via ctrl-alt-v"
  [& args]
  (let [provider (keymaster.core/make-provider)
        lock (ReentrantLock.)]
    (keymaster.core/register provider "control alt V"
                             #((if (.tryLock lock)
                                 (try
                                   ((pasteline.core/paste)
                                    (Thread/sleep 1000))
                                   (finally (.unlock lock)))
                                 ((println "Keybinding recently invoked, ignoring")))))))

