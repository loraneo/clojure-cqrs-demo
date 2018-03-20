(ns commands.hello-world)

(defn canHandle [command])

(defn handle [command]
  (.println 
    (System/out)
    "Hello world query")) 