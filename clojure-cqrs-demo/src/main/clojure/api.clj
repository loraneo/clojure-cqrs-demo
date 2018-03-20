(ns api
  (:require [queries.handlers :as queries])
  (:require [commands.handlers :as commands])
  (:require [clojure.data.json :as json]))

(defn handleQuery [payload]
  (queries/handle (json/read-str payload))
  )

(defn handleCommand [payload]
  (commands/handle (json/read-str payload))
  )

(defn resolveRoute [path payload]
  (cond 
    (.startsWith path "/command") (handleCommand payload)
    (.startsWith path "/query") (handleQuery payload)
    )
  )
(defn handlePostRequest [exchange]
  (.startBlocking exchange)
  (resolveRoute
    (.getRequestURI exchange)
    (with-open [out-data (.getInputStream exchange)]
      (slurp out-data)))
  (.send 
    (.getResponseSender exchange) 
    "Hello world"))

(def createHandler 
  (reify io.undertow.server.HttpHandler
    (handleRequest [this exchange]
      (if (.isInIoThread exchange) (.dispatch exchange this) 
        (handlePostRequest exchange)))))

(def createCommandHandler 
  (io.undertow.Handlers/path) )
(-> (io.undertow.Undertow/builder)
  (.addHttpListener 8080, "localhost")
  (.setHandler createHandler)
  (.build)  
  (.start))


;            DeploymentInfo servletBuilder = deployment()
;                    .setClassLoader(ServletServer.class.getClassLoader())
;                    .setContextPath(MYAPP)
;                    .setDeploymentName("test.war")
;                    .addServlets(
;                            servlet("MessageServlet", MessageServlet.class)
;                                    .addInitParam("message", "Hello World")
;                                    .addMapping("/*"),
;                            servlet("MyServlet", MessageServlet.class)
;                                    .addInitParam("message", "MyServlet")
;                                    .addMapping("/myservlet"));
;
;            DeploymentManager manager = defaultContainer().addDeployment(servletBuilder);
;            manager.deploy();
;
;            HttpHandler servletHandler = manager.start();
;            PathHandler path = Handlers.path(Handlers.redirect(MYAPP))
;                    .addPrefixPath(MYAPP, servletHandler);