package repository.data

import java.io.InputStream
import play.api.libs.json.Json

case class Request(username: String)

object Request {

  def apply(input: InputStream): Request = {
    val username = (Json.parse(input) \ "username").as[String]
    new Request(username)
  }

}
