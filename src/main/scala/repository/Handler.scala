package repository

import java.io.{InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import repository.data.{Request, Response}
import repository.service.GitHubService

class Handler extends RequestStreamHandler {

  override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
    val service = new GitHubService(Config())

    val request = Request(input)
    val repositories = service.repositories(request)

    Response.toOutput(repositories, output)
  }

}