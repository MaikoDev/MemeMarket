name := "meme-market-ui"

version := "0.0.1"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.github.t3hnar" %% "scala-bcrypt" % "latest.release",
  "com.softwaremill.akka-http-session" %% "core" % "latest.release"
  "com.typesafe" % "config" % "latest.release",
  "com.typesafe.akka" %% "akka-http" % "latest.release",
  "com.typesafe.slick" %% "slick" % "latest.release",
  "com.typesafe.slick" %% "slick-hikaricp" % "latest.release",
  "org.json4s" %% "json4s-jackson" % "latest.release",
  "org.postgresql" % "postgresql" % "latest.release",
  "org.slf4j" % "slf4j-nop" % "latest.release"
)