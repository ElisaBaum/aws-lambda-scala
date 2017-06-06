package repository

import repository.util.KMSDecryptionUtil

case class Config(githubApiUrl: String, personalAccessToken: Option[String])

object Config {

  def apply(): Config = {
    val apiUrl = System.getenv("githubApiUrl")
    val accessToken = Option(System.getenv("personalAccessToken")) map KMSDecryptionUtil.decrypt
    new Config(apiUrl, accessToken)
  }

}
