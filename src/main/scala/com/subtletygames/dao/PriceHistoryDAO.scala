package com.subtletygames.dao

import com.subtletygames.entities.PriceHistory

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object PriceHistoryDAO extends DatabaseComponent {
  private val table = "price_history"

  implicit val getPriceHistory =
    GetResult(r => PriceHistory(r.<<, r.<<, r.<<, r.<<, r.<<, r.<<))

  // Create a Price History with the Meme id, opening, closing, high, and low
  def createPriceHistory(
    memeId: Int,
    opening: Double,
    closing: Double,
    high: Double,
    low: Double
  ) {
    val query = sqlu"""
      INSERT INTO #$table(meme_id, opening, closing, high, low)
      VALUES(#$memeId, #$opening, #$closing, #$high, #$low)
    """

    db.run(query).onComplete {
      case Success(s) => println(s"Price History for Meme $memeId created!")
      case Failure(e) => println(e)
    }
  }

  // Find the Price History for a Meme using its id
  def findPriceHistory(memeId: Int) {
    val query = sql"SELECT * FROM #$table".as[PriceHistory]

    db.run(query).onComplete {
      case Success(s) => s foreach { priceHistory => println(priceHistory)}
      case Failure(e) => println(e)
    }
  }

  // Update a Price History
  def updatePriceHistory(priceHistory: PriceHistory) {
    val query = sqlu"""
      UPDATE #$table
      SET
        opening = #${priceHistory.opening},
        closing = #${priceHistory.closing},
        high = #${priceHistory.high},
        low = #${priceHistory.low},
        timestamp = NOW()
      WHERE meme_id = #${priceHistory.memeId}
    """

    db.run(query).onComplete {
      case Success(s) =>
        println(s"Price History for Meme ${priceHistory.memeId} updated!")
      case Failure(e) => println(e)
    }
  }

  // Delete a Price History by its Meme id
  def deletePriceHistory(memeId: Int) {
    val query = sqlu"DELETE FROM #$table WHERE meme_id = #$memeId"

    db.run(query).onComplete {
      case Success(s) => println(s"Price History for Meme $memeId deleted!")
      case Failure(e) => println(e)
    }
  }
}