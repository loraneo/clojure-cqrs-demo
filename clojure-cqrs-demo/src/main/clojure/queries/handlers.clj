(ns queries.handlers
  (:require queries.hello_world_query1)
  (:require queries.hello_world_query2))

(defn getHandlers []
  [queries.hello_world_query1/handler
   queries.hello_world_query2/handler])

(defn findHandler [command]
    (some 
      #(if ((% :canHandle) (command "type")) %)
      (getHandlers)))


(defn execute [payload]
  ((:handle
       (findHandler payload))
      payload))