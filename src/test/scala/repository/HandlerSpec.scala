package repository

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import com.amazonaws.services.lambda.runtime.Context
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}
import repository.service.GitHubService

class HandlerSpec extends FlatSpec with Matchers with MockitoSugar {

  "Handler.handleRequest" should "invoke GitHubService" in {
    val serviceMock = mock[GitHubService]

    val handler = new Handler {
      override val service: GitHubService = serviceMock
    }

    val input = new ByteArrayInputStream(Array.emptyByteArray)
    val output = new ByteArrayOutputStream()

    handler.handleRequest(input, output, mock[Context])

    verify(serviceMock).repositories(input, output)

  }

}

