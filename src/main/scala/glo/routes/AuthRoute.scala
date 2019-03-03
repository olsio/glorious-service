package glo.routes

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.model.Uri.Query
import akka.http.scaladsl.model.{ StatusCodes, Uri }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.util.Timeout
import glo.models.JsonSupport
import glo.routes.util.CodeGenerator

import scala.concurrent.duration._

trait AuthRoute extends JsonSupport {

  implicit def system: ActorSystem

  val timeout = Timeout(5 seconds)

  def authActor: ActorRef

  lazy val authRoute: Route =
    path("auth") {
      get {
        val token = CodeGenerator.generate()
        val uri = Uri("https://app.gitkraken.com/oauth/authorize")
        val parameters: Map[String, String] = Map(
          "response_type" -> "code",
          "client_id" -> "6yayhazte9yd55h26xkm",
          "scope" -> "board:write board:read user:write user:read",
          "state" -> token)
        redirect(uri.withQuery(Query(parameters)), StatusCodes.Found)
      }
    }
}