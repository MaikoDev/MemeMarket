package com.subtletygames

import com.typesafe.config.ConfigFactory

object Config {
  private val c = ConfigFactory.parseResources("dev.conf")

  val dbUrl = c.getString("database.url")
  val dbUser = c.getString("database.user")
  val dbPassword = c.getString("database.password")
  val dbDriver = c.getString("database.driver")
  val dbKeepAlive = c.getBoolean("database.keepAliveConnection")
}