package com.subtletygames.routes

import com.subtletygames.entities.Login
import com.subtletygames.services.LoginFailure
import com.subtletygames.services.TraderService._

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object RootRoute extends LoginProtocol {
  // Handle the GET and POST requests for the root path
  def apply(): Route = {
    pathSingleSlash {
      get {
        getFromFile("views/login.html")
      } ~
      post {
        entity(as[Login]) { loginInfo =>
          onComplete(authenticate(loginInfo)) {
            case Success(s) =>
              println(s)
              complete(s)

            case Failure(e) => e match {
              case LoginFailure(error) =>
                println(error)
                complete(error)

              case t: Throwable =>
                println(t.getStackTrace)
                complete(InternalServerError)
            }
          }
        }
      }
    }
  }
}