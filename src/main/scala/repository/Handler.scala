package repository

import java.io.{InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import repository.service.GitHubService

class Handler extends RequestStreamHandler {

  def service = new GitHubService(Config())

  override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
    service.repositories(input, output)
  }

}