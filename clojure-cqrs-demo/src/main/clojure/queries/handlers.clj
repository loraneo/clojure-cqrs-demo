(ns queries.handlers
(:require queries.hello_world))


(defn handle [payload]
	  (prn "Query:" + payload))