package com.subtletygames

import com.subtletygames.routes._

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

object Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route = {
    RootRoute() ~
    SignupRoute() ~
    // Static files
    pathPrefix("public") {
      getFromDirectory("public")
    } ~
    // Dist files
    pathPrefix("dist") {
      getFromDirectory("dist")
    }
  }

  val port = 8080

  println(s"Listening on port $port")
  Http().bindAndHandle(route, "localhost", port)
}