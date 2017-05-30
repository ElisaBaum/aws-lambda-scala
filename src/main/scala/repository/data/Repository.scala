package repository.data

case class Repository(name: String,
                      description: Option[String] = None,
                      forksCount: Option[Long] = None,
                      starsCount: Option[Long] = None,
                      watchersCount: Option[Long] = None)


object Repository {
  import play.api.libs.functional.syntax._
  import play.api.libs.json._

  implicit val RepositoryReads: Reads[Repository] = (
    (__ \ "name").read[String] and
      (__ \ "description").readNullable[String] and
      (__ \ "forks_count").readNullable[Long] and
      (__ \ "stargazers_count").readNullable[Long] and
      (__ \ "watchers_count").readNullable[Long]
    ) (Repository.apply _)

  implicit val RepositoryWrites: Writes[Repository] = Json.writes[Repository]

}