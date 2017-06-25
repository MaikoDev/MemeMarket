package com.subtletygames

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.HttpApp
import akka.http.scaladsl.server.Route

import scala.util.Properties

object Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route = {
    pathSingleSlash {
      get {
        getFromFile("index.html")
      }
    } ~
    // Static files
    pathPrefix("public") {
      getFromDirectory("public")
    } ~
    // Dist files
    pathPrefix("dist") {
      getFromDirectory("dist")
    }
  }

  val port = Properties.envOrElse("PORT", "8080").toInt

  println(s"Listening on port $port")
  Http().bindAndHandle(route, "localhost", port)
}