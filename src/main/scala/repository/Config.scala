package repository

import repository.util.KMSDecryptionUtil

case class Config(githubApiUrl: String, personalAccessToken: Option[String])

object Config {

  def apply(): Config = {
    val apiUrl = System.getenv("githubApiUrl")

    val accessToken = (System.getenv("personalAccessToken") match {
      case token if token.isEmpty => None
      case token => Some(token)
    }) map KMSDecryptionUtil.decrypt


    new Config(apiUrl, accessToken)
  }

}
