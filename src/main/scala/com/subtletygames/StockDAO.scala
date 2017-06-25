package com.subtletygames

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

object StockDAO extends SlickDBComponent {
  implicit val getStock = GetResult(r => Stock(r.<<, r.<<, r.<<))

  // Create a Stock with the name and price
  def createStock(name: String, price: Double) {
    val table = "stock"
    val query =
      sqlu"INSERT INTO #$table(name, price) VALUES('#$name', #$price)"

    db.run(query) onComplete {
      case Success(s) => println(s"Stock $name created!")
      case Failure(f) => println("Stock name already exists!")
    }
  }

  // Find a Stock by its id
  def findStock(id: Int) {
    val table = "stock"
    val query = sql"SELECT * FROM #$table WHERE id = #$id".as[Stock]

    db.run(query) onComplete {
      case Success(s) => s foreach { stock => println(stock)}
      case Failure(f) => println(f)
    }
  }
}