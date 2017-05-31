package repository.service

import java.io.{InputStream, OutputStream}

import play.api.libs.json.Json
import repository.Config
import repository.data.{Request, Response}

import scalaj.http.Http

class GitHubService(config: Config) {

  def repositories(input: InputStream, output: OutputStream): Unit = {
    val request = Request(input)

    val httpRequest = buildRepositoriesRequest(request)
    val httpResponse = httpRequest.asString

    val response = Response(Json.parse(httpResponse.body))
    Response.toOutput(response, output)
  }

  private def buildRepositoriesRequest(request: Request) = {
    val apiUrl = s"${config.githubApiUrl}/users/${request.username}/repos"
    Http(apiUrl)
      .header("Accept", "application/json")
      .header("Authorization", s"token ${config.personalAccessToken}")
  }

}

