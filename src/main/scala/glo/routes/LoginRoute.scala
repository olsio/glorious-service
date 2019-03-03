package glo.routes

import akka.http.scaladsl.server.Directives.{as, entity}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.post
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import glo.models.{AccessToken, JsonSupport}

trait LoginRoute extends JsonSupport {

  lazy val loginRoute: Route =
    path("login") {
      post {
        entity(as[AccessToken]) { accessToken =>
          complete(accessToken)
        }
      }
    }
}