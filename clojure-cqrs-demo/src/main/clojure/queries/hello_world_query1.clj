(ns queries.hello_world_query1)


(defn canHandle [type]
  (prn type)
  (if (=
        (compare "commands.hello_world_query1" type) 0) true false))


(defn handle [command]
  "Query response 1")


(def handler {:canHandle canHandle
              :handle    handle})