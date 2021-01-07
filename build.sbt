import scala.languageFeature.postfixOps

name := "Minesweeper"

version := "0.1"

scalacOptions ++= Seq("-language:postfixOps")

scalaVersion := "3.0.0-M2"

// libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
// libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2" % "test"

libraryDependencies += "org.scalactic" % "scalactic_2.13" % "3.2.2"
libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.2.2" % "test"

// libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.2"
// libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.2"
