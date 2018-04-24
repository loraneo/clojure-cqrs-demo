(ns api
  (:require [queries.handlers :as queries])
  (:require [commands.handlers :as commands])
  (:require [clojure.data.json :as json]))

(defn handleQuery [payload]
  (queries/execute (json/read-str payload))
  )

(defn handleCommand [payload]
  (commands/execute (json/read-str payload))
  )

(defn resolveRoute [path payload]
  (cond
    (.startsWith path "/command") (handleCommand payload)
    (.startsWith path "/query") (handleQuery payload)
    )
  )
(defn handlePostRequest [exchange]
  (.startBlocking exchange)
  (.send
    (.getResponseSender exchange)
    (resolveRoute
      (.getRequestURI exchange)
      (with-open [out-data (.getInputStream exchange)]
        (slurp out-data)))))

(def createHandler
  (reify io.undertow.server.HttpHandler
    (handleRequest [this exchange]
      (if (.isInIoThread exchange) (.dispatch exchange this)
                                   (handlePostRequest exchange)))))

(def createCommandHandler
  (io.undertow.Handlers/path))
(-> (io.undertow.Undertow/builder)
    (.addHttpListener 8080, "localhost")
    (.setHandler createHandler)
    (.build)
    (.start))

