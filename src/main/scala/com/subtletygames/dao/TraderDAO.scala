package com.subtletygames.dao

import com.subtletygames.entities.{Signup, Trader}

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

object TraderDAO extends DatabaseComponent {
  implicit val getTrader = GetResult(r => Trader(r.<<, r.<<, r.<<, r.<<))

  val table = "trader"

  // Create a Trader with the username, email, password
  def createTrader(
    username: String,
    email: String,
    password: String
  ): Future[Int] = {
    val query = sqlu"""
      INSERT INTO #$table(username, email, password)
      VALUES('#$username', '#$email', '#$password')
    """

    db.run(query)
  }

  // Find a Trader by their id
  def findTraderById(id: Int) : Future[Seq[Trader]] ={
    db.run(sql"SELECT * FROM #$table WHERE id = #$id".as[Trader])
  }

  // Find a trader by their email
  def findTraderByEmail(email: String): Future[Seq[Trader]] = {
    db.run(sql"SELECT * FROM #$table WHERE email = '#$email'".as[Trader])
  }

  // Update a Trader
  def updateTrader(trader: Trader) {
    val query = sqlu"""
      UPDATE #$table
      SET
        username = '#${trader.username}',
        email = '#${trader.email}'
      WHERE
        id = #${trader.id}
    """

    db.run(query).onComplete {
      case Success(s) => println(s"Trader ${trader.id} updated!")
      case Failure(f) => println(f)
    }
  }

  // Delete a Trader by their id
  def deleteTrader(id: Int) {
    val query = sqlu"DELETE FROM #$table WHERE id = #$id"

    db.run(query).onComplete {
      case Success(s) => println(s"Trader $id deleted!")
      case Failure(f) => println(f)
    }
  }
}