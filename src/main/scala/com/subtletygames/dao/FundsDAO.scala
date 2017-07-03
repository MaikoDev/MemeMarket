package com.subtletygames.dao

import com.subtletygames.entities.Funds

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object FundsDAO extends DatabaseComponent {
  private val table = "funds"

  implicit val getFunds = GetResult(r => Funds(r.<<, r.<<))

  // Create Funds with the Trader id and amount
  def createFunds(traderId: Int, amount: Double) {
    val query = sqlu"INSERT INTO #$table VALUES(#$traderId, #$amount)"

    db.run(query).onComplete {
      case Success(s) => println(s"Funds for Trader $traderId created!")
      case Failure(e) => println(e)
    }
  }

  // Find Funds by the Trader id
  def findFunds(traderId: Int) {
    val query =
      sql"SELECT * FROM #$table WHERE trader_id = #$traderId".as[Funds]

    db.run(query).onComplete {
      case Success(s) => s foreach { trader => println(trader) }
      case Failure(e) => println(e)
    }
  }

  // Update Funds
  def updateFunds(funds: Funds) {
    val query = sqlu"""
      UPDATE #$table
      SET amount = #${funds.amount}
      WHERE trader_id = #${funds.traderId}
    """

    db.run(query).onComplete {
      case Success(s) => println(s"Funds ${funds.traderId} updated!")
      case Failure(e) => println(e)
    }
  }

  // Delete Funds by its the Trader id
  def deleteFunds(traderId: Int) {
    val query = sqlu"DELETE FROM #$table WHERE trader_id = #$traderId"

    db.run(query).onComplete {
      case Success(s) => println(s"Funds belong to $traderId deleted!")
      case Failure(e) => println(e)
    }
  }
}