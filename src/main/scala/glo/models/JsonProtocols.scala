package glo.models

import spray.json.{ DefaultJsonProtocol, DeserializationException, JsObject, JsString, JsValue, RootJsonFormat }

object JsonProtocols extends DefaultJsonProtocol {

  implicit object AccessTokenJsonFormat extends RootJsonFormat[AccessToken] {
    def write(c: AccessToken) = JsObject(
      "access_token" -> JsString(c.accessToken),
      "token_type" -> JsString(c.tokenType))

    def read(value: JsValue) = {
      value.asJsObject.getFields("access_token", "token_type") match {
        case Seq(JsString(accessToken), JsString(tokenType)) =>
          new AccessToken(accessToken, tokenType)
        case _ => throw new DeserializationException("AccessToken expected")
      }
    }
  }

}