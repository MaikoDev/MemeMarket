package com.subtletygames

sealed abstract class Entity

case class Trader(
  id: Option[Int],
  username: String,
  email: String
) extends Entity

case class Stock(
  id: Option[Int],
  name: String,
  price: Double
) extends Entity

case class Event(id: Option[Int], content: String) extends Entity

case class Meme(
  id: Option[Int],
  stockId: Option[Int],
  name: String,
  fvr: Double,
  stockCount: Int
) extends Entity

case class Funds(traderId: Int, amount: Double) extends Entity

case class News(
  memeId: Int,
  eventId: Int,
  timestamp: String = "now()"
) extends Entity

case class PriceHistory(
  memeId: Int,
  opening: Double,
  closing: Double,
  high: Double,
  low: Double,
  timestamp: String = "now()"
) extends Entity

case object Trader extends Entity
case object Stock extends Entity
case object Event extends Entity
case object Meme extends Entity
case object Funds extends Entity
case object News extends Entity
case object PriceHistory extends Entity