package repository.service

import play.api.libs.json.Json
import repository.Config
import repository.data.{Request, Response}

import scalaj.http.Http

class GitHubService(config: Config) {

  def repositories(request: Request): Response = {
    val httpRequest = buildRepositoriesRequest(request)
    val response = httpRequest.asString
    Response(Json.parse(response.body))
  }

  private def buildRepositoriesRequest(request: Request) = {
    val apiUrl = s"${config.githubApiUrl}/users/${request.username}/repos"
    Http(apiUrl)
      .header("Accept", "application/json")
      .header("Authorization", s"token ${config.personalAccessToken}")
  }

}

