package com.subtletygames.routes

import com.subtletygames.entities.Signup
import com.subtletygames.services.SignupFailure
import com.subtletygames.services.TraderService._

import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object SignupRoute extends SignupProtocol {
  // Handle the GET and POST requests for the Signup path
  def apply(): Route = {
    path("signup") {
      get {
        getFromFile("views/signup.html")
      } ~
      post {
        entity(as[Signup]) { signupInfo =>
          onComplete(signup(signupInfo)) {
            case Success(s) =>
              println(s)
              // redirect("/", Found)
              complete(s)

            case Failure(e) => e match {
              case SignupFailure(error) =>
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