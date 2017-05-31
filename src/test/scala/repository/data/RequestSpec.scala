package repository.data

import java.io.ByteArrayInputStream

import org.scalatest.{FlatSpec, Matchers}
import play.api.libs.json.Json

class RequestSpec extends FlatSpec with Matchers {

  "Request.apply" should "be create Request with given username in json input stream" in {
    val input = new ByteArrayInputStream(Json.obj("username" -> "TestUser").toString().getBytes)

    Request(input).username shouldBe "TestUser"
  }

}
