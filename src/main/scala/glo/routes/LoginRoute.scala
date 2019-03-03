package glo.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import glo.models.{AuthResponse, JsonSupport}

trait LoginRoute extends JsonSupport {

  lazy val loginRoute: Route =
    path("login") {
      get {
        parameters('code.as[String], 'state.as[String]) { (code, state) =>
          val response = AuthResponse(code, state)
          complete(response)
        }
      }
    }
}