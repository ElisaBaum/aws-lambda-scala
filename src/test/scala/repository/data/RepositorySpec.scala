package repository.data

import org.scalatest._
import org.scalatest.OptionValues._
import play.api.libs.json._

class RepositorySpec extends FlatSpec with Matchers {

  "Repository" should "be created correctly from complete json" in {
    val completeJsonRepo = Json.obj(
      "name" -> "Repo1",
      "description" -> "Some description",
      "forks_count" -> 0,
      "stargazers_count" -> 10,
      "watchers_count" -> 1
    )

    completeJsonRepo.validate[Repository] match {

      case JsSuccess(repo, _) =>
        repo.name shouldBe "Repo1"
        repo.description.value shouldBe "Some description"
        repo.forksCount.value shouldBe 0
        repo.starsCount.value shouldBe 10
        repo.watchersCount.value shouldBe 1

      case JsError(error) => fail(s"Could not parse repository: $error")
    }
  }

  it should "be created correctly from minimal json" in {
    val minimalJsonRepo = Json.obj(
      "name" -> "Repo1",
      "description" -> JsNull,
      "forks_count" -> JsNull,
      "stargazers_count" -> JsNull,
      "watchers_count" -> JsNull
    )

    minimalJsonRepo.validate[Repository] match {

      case JsSuccess(repo, _) =>
        repo.name shouldBe "Repo1"
        repo.description shouldBe None
        repo.forksCount shouldBe None
        repo.starsCount shouldBe None
        repo.watchersCount shouldBe None

      case JsError(error) => fail(s"Could not parse repository: $error")
    }
  }

  it should "be writable as json" in {
    val repository = Repository("Repo1")

    (Json.toJson(repository) \ "name").as[String] shouldBe "Repo1"
  }
}
