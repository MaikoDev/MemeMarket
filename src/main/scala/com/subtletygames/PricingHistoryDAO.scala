package com.subtletygames

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

object PricingHistoryDAO extends SlickDBComponent {
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
    val table = "price_history"
    val query = sqlu"""
      INSERT INTO #$table(meme_id, opening, closing, high, low)
      VALUES(#$memeId, #$opening, #$closing, #$high, #$low)
    """

    db.run(query) onComplete {
      case Success(s) => println(s"Price History for Meme $memeId created!")
      case Failure(f) => println(f)
    }
  }

  // Find the Price History for a Meme using its id
  def findPriceHistory(memeId: Int) {
    val table = "price_history"
    val query = sql"SELECT * FROM #$table".as[PriceHistory]

    db.run(query) onComplete {
      case Success(s) => s foreach { priceHistory => println(priceHistory)}
      case Failure(f) => println(f)
    }
  }
}