(ns commands.handlers
(:require commands.hello_world))


(defn getHandlers []
  ([commands.hello-world/canHandle]))

(defn findHandler [type]
  (doseq [handler (getHandlers)]
    (prn (handler 1))))


(defn handle [payload]
  (doseq [command payload] 
    (prn 
      (str "Command:"  (get command type)))
    (findHandler command)))