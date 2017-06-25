package com.subtletygames

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

object Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route = {
    pathSingleSlash {
      complete("Hello, world!")
    }
  }

  val port = 8080

  println(s"Listening on port $port")
  Http().bindAndHandle(route, "localhost", port)
}