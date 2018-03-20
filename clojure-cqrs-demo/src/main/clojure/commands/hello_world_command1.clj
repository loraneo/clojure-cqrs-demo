(ns commands.hello_world_command1)

(defn canHandle [type]
  (if 
    (= (compare "commands/hello-world1" type) 0) true false))

(defn handle [command]
  (prn
     "Hello world command 1"))




(def handler {:canHandle canHandle
              :handle handle})