package glo.models

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

trait JsonSupport extends SprayJsonSupport {

  import glo.models.JsonProtocols._

  implicit val authResponseJsonFormat = jsonFormat2(AuthResponse)
  implicit val accessTokenJsonFormat = AccessTokenJsonFormat
}
