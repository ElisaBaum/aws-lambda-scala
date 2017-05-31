package repository.data

import java.io.OutputStream

import play.api.libs.json._

case class Response(repositories: Vector[Repository])

object Response {

  implicit val responseWrites: Writes[Response] = Json.writes[Response]

  def apply(repositories: Vector[Repository]): Response = new Response(repositories)

  def apply(json: JsValue): Response = {
    json.validate[Vector[Repository]] match {
      case JsSuccess(repos, _) => new Response(repos)
      case JsError(error) => throw new Exception(s"Could not parse repositories: $error")
    }
  }

  def toOutput(repsonse: Response, output: OutputStream): OutputStream = {
    output.write(Json.toJson(repsonse).toString().getBytes)
    output
  }

}
