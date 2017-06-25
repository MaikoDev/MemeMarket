package com.subtletygames

import com.github.t3hnar.bcrypt._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

object FundsDAO extends SlickDBComponent {
  implicit val getFunds = GetResult(r => Funds(r.<<, r.<<))

  // Create Funds with the Trader id and amount
  def createFunds(traderId: Int, amount: Double) {
    val table = "funds"
    val query = sqlu"INSERT INTO #$table VALUES(#$traderId, #$amount)"

    db.run(query) onComplete {
      case Success(s) => println(s"Funds for Trader $traderId created!")
      case Failure(f) => println(f)
    }
  }

  // Find Funds by the Trader id
  def findFunds(traderId: Int) {
    val table = "funds"
    val query =
      sql"SELECT * FROM #$table WHERE trader_id = #$traderId".as[Funds]

    db.run(query) onComplete {
      case Success(s) => s foreach { trader => println(trader)}
      case Failure(f) => println(f)
    }
  }
}