package com.subtletygames.dao

import com.subtletygames.entities.Stock

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object StockDAO extends DatabaseComponent {
  private val table = "stock"

  implicit val getStock = GetResult(r => Stock(r.<<, r.<<, r.<<))

  // Create a Stock with the name and price
  def createStock(name: String, price: Double) {
    val query =
      sqlu"INSERT INTO #$table(name, price) VALUES('#$name', #$price)"

    db.run(query).onComplete {
      case Success(s) => println(s"Stock $name created!")
      case Failure(e) => println("Stock name already exists!")
    }
  }

  // Find a Stock by its id
  def findStock(id: Int) {
    val query = sql"SELECT * FROM #$table WHERE id = #$id".as[Stock]

    db.run(query).onComplete {
      case Success(s) => s foreach { stock => println(stock)}
      case Failure(e) => println(e)
    }
  }

  // Update a Stock
  def updateStock(stock: Stock) {
    val query = sqlu"""
      UPDATE #$table
      SET
        name = '#${stock.name}',
        price = #${stock.price}
      WHERE id = #${stock.id}
    """

    db.run(query).onComplete {
      case Success(s) => println(s"Stock ${stock.id} updated!")
      case Failure(e) => println(e)
    }
  }

  // Delete a Stock by its id
  def deleteStock(id: Int) {
    val query = sqlu"DELETE FROM #$table WHERE id = #$id"

    db.run(query).onComplete {
      case Success(s) => println(s"Stock $id deleted!")
      case Failure(e) => println(e)
    }
  }
}