package com.subtletygames

import com.github.t3hnar.bcrypt._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

object TraderDAO extends SlickDBComponent {
  implicit val getTrader = GetResult(r => Trader(r.<<, r.<<, r.<<))

  // Create a Trader with the username, email, password
  def createTrader(username: String, email: String, password: String) {
    val table = "trader"
    val query = sqlu"""
      INSERT INTO #$table(username, email, password)
      VALUES('#$username', '#$email', '#${password.bcrypt}')
    """

    db.run(query) onComplete {
      case Success(s) => println(s"Trader $username created!")
      case Failure(f) => println("Username and/or email already exists!")
    }
  }

  // Find a Trader by their id
  def findTrader(id: Int) {
    val table = "trader"
    val query =
      sql"SELECT id, username, email FROM #$table WHERE id = #$id".as[Trader]

    db.run(query) onComplete {
      case Success(s) => s foreach { trader => println(trader)}
      case Failure(f) => println(f)
    }
  }
}