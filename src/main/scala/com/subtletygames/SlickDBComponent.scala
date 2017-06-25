package com.subtletygames

import slick.driver.PostgresDriver.api._

trait SlickDBComponent {
  lazy val db = Database.forConfig("slick.db")
}