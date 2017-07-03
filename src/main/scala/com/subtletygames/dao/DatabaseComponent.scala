package com.subtletygames.dao

import slick.driver.PostgresDriver.api.Database

trait DatabaseComponent {
  lazy val db = Database.forConfig("app.slick.db")
}