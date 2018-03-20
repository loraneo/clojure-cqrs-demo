(ns queries.hello_world_query1)


(defn canHandle [type]
  (if 
    (compare "commands.hello_world_query1" type) true false))

(defn handle [command]
  "Query response 1")


(def handler {:canHandle canHandle
              :handle handle})