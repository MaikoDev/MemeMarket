package com.subtletygames.dao

import com.subtletygames.entities.Meme

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object MemeDAO extends DatabaseComponent {
  private val table = "meme"

  implicit val getMeme = GetResult(r => Meme(r.<<, r.<<, r.<<, r.<<, r.<<))

  // Create a Meme with the name, stock count, and FVR
  def createMeme(stockId: Int, name: String, stockCount: Int, fvr: Double) {
    val query = sqlu"""
      INSERT INTO #$table(stock_id, name, stock_count, fvr)
      VALUES(#$stockId, '#$name', #$stockCount, #$fvr)
    """

    db.run(query).onComplete {
      case Success(s) => println(s"Meme $name created!")
      case Failure(e) => println(e)
    }
  }

  // Find a Meme by its id
  def findMeme(id: Int) {
    val query = sql"SELECT * FROM #$table WHERE id = #$id".as[Meme]

    db.run(query).onComplete {
      case Success(s) => s foreach { meme => println(meme) }
      case Failure(e) => println(e)
    }
  }

  // Update a Meme
  def updateMeme(meme: Meme) {
    val query = sqlu"""
      UPDATE #$table
      SET
        stock_id = #${meme.stockId},
        name = '#${meme.name}',
        stock_count = #${meme.stockCount},
        fvr = #${meme.fvr},
      WHERE id = #${meme.id}
    """

    db.run(query).onComplete {
      case Success(s) => println(s"Meme ${meme.id} updated!")
      case Failure(e) => println(e)
    }
  }

  // Delete a Meme by its id
  def deleteMeme(id: Int) {
    val query = sqlu"DELETE FROM #$table WHERE id = #$id"

    db.run(query).onComplete {
      case Success(s) => println(s"Meme $id deleted!")
      case Failure(e) => println(e)
    }
  }
}