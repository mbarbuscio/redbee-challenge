name := """challenge"""
organization := "com.mbarbuscio"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.1" % Test
libraryDependencies += "org.typelevel" %% "cats-core" % "1.6.0"
libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "8.0.13",
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "com.h2database" % "h2" % "1.4.187",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0"

)
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "4.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "4.0.0"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.mbarbuscio.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.mbarbuscio.binders._"
