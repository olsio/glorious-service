package glo

import akka.http.scaladsl.server.Directives._

trait StaticRoutes {

  val staticRoutes =
    (pathSingleSlash) {
      getFromResource("static/index.html")
    } ~ {
      getFromResourceDirectory("static")
    }
}

