package repository

import provider.util.KMSDecryptionUtil

case class Config(githubApiUrl: String, personalAccessToken: String)

object Config {

  def apply(): Config = {
    val apiUrl = System.getenv("githubApiUrl")
    val accessToken = KMSDecryptionUtil.decrypt(System.getenv("personalAccessToken"))
    new Config(apiUrl, accessToken)
  }

}
