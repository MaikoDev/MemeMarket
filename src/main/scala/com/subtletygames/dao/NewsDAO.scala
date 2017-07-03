package com.subtletygames.dao

import com.subtletygames.entities.News

import org.json4s._
import org.json4s.jackson.JsonMethods._
import slick.driver.PostgresDriver.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object NewsDAO extends DatabaseComponent {
  private val table = "news"

  implicit val getNews = GetResult(r => News(r.<<, r.<<, r.<<))

  // Create News the content
  def createNews(content: String) {
    val query =
      sqlu"INSERT INTO #$table(content) VALUES(#$content)"

    db.run(query).onComplete {
      case Success(s) => println(s"News created!")
      case Failure(e) => println(e)
    }
  }

  // Find News by its id
  def findNews(id: Int) {
    val query = sql"SELECT * FROM #$table WHERE id = #$id".as[News]

    db.run(query).onComplete {
      case Success(s) => s foreach { news => println(news) }
      case Failure(e) => println(e)
    }
  }

  // Update News
  def updateNews(news: News) {
    val query = sqlu"""
      UPDATE #$table
      SET
        content = '#${news.content}',
        timestamp = NOW()
      WHERE id = #${news.id}
    """

    db.run(query).onComplete {
      case Success(s) => println(s"News ${news.id} updated!")
      case Failure(e) => println(e)
    }
  }

  // Delete News by its id
  def deleteNews(id: Int) {
    val query = sqlu"DELETE FROM #$table WHERE id = #$id"

    db.run(query).onComplete {
      case Success(s) => println(s"News $id deleted!")
      case Failure(e) => println(e)
    }
  }
}