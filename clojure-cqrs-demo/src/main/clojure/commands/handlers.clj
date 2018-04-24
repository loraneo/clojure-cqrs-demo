(ns commands.handlers
  (:require commands.hello_world_command1)
  (:require commands.hello_world_command2))


(defn getHandlers []
  [commands.hello_world_command1/handler
   commands.hello_world_command2/handler])

(defn findHandler [command]
    (some 
      #(if ((% :canHandle) (command "type")) %)
      (getHandlers)))


(defn execute [payload]
  (doseq [command payload] 
    ((:handle 
       (findHandler command))
      payload))
  "Hello")