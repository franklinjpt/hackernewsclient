name := "HackerNewsClient"
organization := "cz.cvut.fit.bioop"
version := "1.0"

scalaVersion := "2.13.10"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
libraryDependencies += "org.mockito" % "mockito-scala_2.13" % "1.17.12" % "test"

// JSON parsing library
libraryDependencies += "com.lihaoyi" %% "upickle" % "2.0.0"

assembly / assemblyJarName := "HackerNewsClient.jar"
