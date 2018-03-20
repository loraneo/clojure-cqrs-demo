(ns queries.hello_world_query2)

(defn canHandle [type]
  (if 
    (compare "commands.hello_world_query2" type) true false))

(defn handle [command]
  "Query response 2")


(def handler {:canHandle canHandle
              :handle handle})