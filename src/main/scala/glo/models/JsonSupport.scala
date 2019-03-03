package glo.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import glo.repositories.UserRegistryActor.ActionPerformed
import glo.repositories.{User, Users}

trait JsonSupport extends SprayJsonSupport {

  import glo.models.JsonProtocols._

  implicit val userJsonFormat = jsonFormat3(User)
  implicit val usersJsonFormat = jsonFormat1(Users)
  implicit val actionPerformedJsonFormat = jsonFormat1(ActionPerformed)
  implicit val accessTokenJsonFormat = AccessTokenJsonFormat
}
