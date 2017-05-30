
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      scalaVersion := "2.12.2",
      version      := "1.0"
    )),
    name := "GitHub Repositories Lambda Test",

    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.1" % Test,
      "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
      "com.amazonaws" % "aws-java-sdk-kms" % "1.11.135",
      "com.typesafe.play" %% "play-json" % "2.6.0-M1",
      "org.scalaj" %% "scalaj-http" % "2.3.0"
    )
  )
