(ns commands.hello_world_command2)

(defn canHandle [type]
  (if 
    (compare "commands/hello-world2" type) true false))

(defn handle [command]
  (prn
     "Hello world command 2"))




(def handler {:canHandle canHandle
              :handle handle})