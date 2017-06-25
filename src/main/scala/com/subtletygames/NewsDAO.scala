package com.subtletygames

import com.github.t3hnar.bcrypt._
import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Success, Failure}

object NewsDAO extends SlickDBComponent {
  implicit val getNews = GetResult(r => News(r.<<, r.<<, r.<<))

  // Create News with the Meme id and the Event id
  def createNews(memeId: Int, eventId: Int) {
    val table = "news"
    val query =
      sqlu"INSERT INTO #$table(meme_id, event_id) VALUES(#$memeId, #$eventId)"

    db.run(query) onComplete {
      case Success(s) =>
        println(s"News for Meme $memeId with Event $eventId created!")
      case Failure(f) => println(f)
    }
  }

  // Find all News for a Meme using its id along with an Event id
  def findNews(memeId: Int, eventId: Int) {
    val table = "news"
    val query = sql"SELECT * FROM #$table".as[News]

    db.run(query) onComplete {
      case Success(s) => s foreach { news => println(news)}
      case Failure(f) => println(f)
    }
  }
}