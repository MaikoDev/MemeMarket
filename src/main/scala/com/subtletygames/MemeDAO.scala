package com.subtletygames

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

object MemeDAO extends SlickDBComponent {
  implicit val getMeme = GetResult(r => Meme(r.<<, r.<<, r.<<, r.<<, r.<<))

  // Create a Meme with the name, stock count, and FVR
  def createMeme(stockId: Int, name: String, stockCount: Int, fvr: Double) {
    val table = "meme"
    val query = sqlu"""
      INSERT INTO #$table(stock_id, name, stock_count, fvr)
      VALUES(#$stockId, '#$name', #$stockCount, #$fvr)
    """

    db.run(query) onComplete {
      case Success(s) => println(s"Meme $name created!")
      case Failure(f) => println(f)
    }
  }

  // Find a Meme by its id
  def findMeme(id: Int) {
    val table = "meme"
    val query = sql"SELECT * FROM #$table WHERE id = #$id".as[Meme]

    db.run(query) onComplete {
      case Success(s) => s foreach { meme => println(meme)}
      case Failure(f) => println(f)
    }
  }
}