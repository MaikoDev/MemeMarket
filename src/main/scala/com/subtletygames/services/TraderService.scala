package com.subtletygames.services

import com.github.t3hnar.bcrypt._
import com.subtletygames.dao.TraderDAO._
import com.subtletygames.entities.{Login, Signup}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.{Failure, Success}

case class LoginFailure(error: String) extends Exception(error)
case class SignupFailure(error: String) extends Exception(error)

object TraderService {
  // Authenticate the Login submitted by the user
  def authenticate(login: Login): Future[String] = {
    val loginPromise = Promise[String]()

    findTraderByEmail(login.email).onComplete {
      case Success(s) =>
        // Trader found, continue with authentication
        if (s.size > 0) {
          val trader = s(0)

          if (login.password.isBcrypted(trader.password)) {
            loginPromise.success("")
          } else {
            loginPromise.failure(
              LoginFailure("The email/password you entered is incorrect!")
            )
          }
        }
        // No Trader exists with the email
        else {
          loginPromise.failure(
            LoginFailure("The email/password you entered is incorrect!")
          )
        }

      case Failure(e) => loginPromise.failure(LoginFailure(e.toString))
    }

    loginPromise.future
  }

  def signup(signup: Signup): Future[String] = {
    val signupPromise = Promise[String]()

    findTraderByEmail(signup.email).onComplete {
      case Success(s) =>
        // Trader already exists with the email
        if (s.size > 0) {
          signupPromise.failure(
            SignupFailure(
              "There is already an account with the email you provided!"
            )
          )
        }
        // No Trader exists with the email, continue with signing up
        else {
          val username = signup.username
          val email = signup.email
          // Encrypt the password
          val encryptedPassword = signup.password.bcrypt

          createTrader(username, email, encryptedPassword).onComplete {
            // Trader successfully registered
            case Success(s) =>
              signupPromise.success(
                s"You have successfuly created the Trader $username!"
              )

            // The username provided is already being used
            case Failure(e) =>
              signupPromise.failure(
                SignupFailure("The username you provided already exists!")
              )
          }
        }

      case Failure(e) => signupPromise.failure(SignupFailure(e.toString))
    }

    signupPromise.future
  }
}