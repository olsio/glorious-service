package glo.routes

//#user-routes-spec
//#test-top
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import glo.models.AccessToken
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

class LoginRouteSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest
  with LoginRoute {

  "be able to add token (POST /login)" in {
    val acccessToken = AccessToken("TOKEN", "TYPE")
    val acccessTokenEntity = Marshal(acccessToken).to[MessageEntity].futureValue

    val request = Post("/login").withEntity(acccessTokenEntity)

    request ~> loginRoute ~> check {
      status should ===(StatusCodes.OK)


      entityAs[String] should ===("""{"access_token":"TOKEN","token_type":"TYPE"}""")
    }
  }
}
