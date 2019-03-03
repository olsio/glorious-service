package glo.actors

import akka.actor.{ Actor, ActorLogging, Props }

object AuthActor {

  final case class StartAuth(code: String)

  final case class CompleteAuth(code: String)

  def props: Props = Props[AuthActor]
}

class AuthActor extends Actor with ActorLogging {

  import AuthActor._

  var authRequests: Map[String, String] = Map()

  def receive: Receive = {
    case StartAuth(code) =>
      authRequests += (code -> "")
    case CompleteAuth(code) =>
      log.info(code)
  }
}
