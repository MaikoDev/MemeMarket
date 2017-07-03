package com.subtletygames.entities

case class Meme(
  id: Option[Int],
  stockId: Option[Int],
  name: String,
  fvr: Double,
  stockCount: Int
)