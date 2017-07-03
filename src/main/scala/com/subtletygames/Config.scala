package com.subtletygames

import com.softwaremill.session.{SessionConfig, SessionManager}
import com.softwaremill.session.SessionDirectives._
import com.softwaremill.session.SessionOptions._
import com.typesafe.config.ConfigFactory

object Config {
  private val c = ConfigFactory.parseResources("dev.conf")

  // Database credentials
  val dbUrl = c.getString("app.slick.db.url")
  val dbUser = c.getString("app.slick.db.user")
  val dbPassword = c.getString("app.slick.db.password")
  val dbDriver = c.getString("app.slick.db.driver")

  // Session management
  val sessionSecret = c.getString("app.session.secret")
  val sessionConfig = SessionConfig.default(Config.sessionSecret)
  implicit val sessionManager = new SessionManager[Long](sessionConfig)
}