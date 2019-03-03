package glo

import akka.http.scaladsl.server.Directives._

trait StaticRoutes {

  val staticRoutes = get {
    pathSingleSlash {
      getFromResource("index.html")
    } ~ pathPrefix("static") {
      getFromResourceDirectory("static")
    }
  }
}

