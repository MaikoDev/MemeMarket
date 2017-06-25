package com.subtletygames

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

object EventDAO extends SlickDBComponent {
  implicit val getEvent = GetResult(r => Event(r.<<, r.<<))

  // Create an Event with the content
  def createEvent(content: String) {
    val table = "event"
    val query = sqlu"INSERT INTO #$table(content) VALUES('#$content')"

    db.run(query) onComplete {
      case Success(s) => println(s"Event created!")
      case Failure(f) => println(f)
    }
  }

  // Find an Event by its id
  def findEvent(id: Int) {
    val table = "event"
    val query = sql"SELECT * FROM #$table WHERE id = #$id".as[Event]

    db.run(query) onComplete {
      case Success(s) => s foreach { event => println(event)}
      case Failure(f) => println(f)
    }
  }
}