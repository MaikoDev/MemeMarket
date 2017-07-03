package com.subtletygames.entities

case class PriceHistory(
  memeId: Int,
  opening: Double,
  closing: Double,
  high: Double,
  low: Double,
  timestamp: String = "NOW()"
)