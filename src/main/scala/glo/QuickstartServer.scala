package glo

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.stream.ActorMaterializer
import glo.actors.AuthActor
import glo.routes.{ AuthRoute, LoginRoute, StaticRoutes }

import scala.concurrent.duration.Duration
import scala.concurrent.{ Await, ExecutionContext, Future }
import scala.util.{ Failure, Properties, Success }

object QuickstartServer extends App with StaticRoutes with LoginRoute with AuthRoute {

  implicit val system: ActorSystem = ActorSystem("helloAkkaHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  val authActor: ActorRef = system.actorOf(AuthActor.props, "authActor")

  lazy val routes: Route = staticRoutes ~ loginRoute ~ authRoute

  val port = Properties.envOrElse("PORT", "8080").toInt

  val clientRouteLogged = DebuggingDirectives.logRequestResult("Routes", Logging.InfoLevel)(routes)

  val serverBinding: Future[Http.ServerBinding] = Http().bindAndHandle(clientRouteLogged, "0.0.0.0", port)

  serverBinding.onComplete {
    case Success(bound) =>
      println(s"Server online at http://${bound.localAddress.getHostString}:${bound.localAddress.getPort}/")
    case Failure(e) =>
      Console.err.println(s"Server could not start!")
      e.printStackTrace()
      system.terminate()
  }

  Await.result(system.whenTerminated, Duration.Inf)
}
