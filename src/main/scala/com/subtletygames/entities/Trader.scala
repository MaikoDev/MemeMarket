package com.subtletygames.entities

case class Trader(
  id: Option[Int],
  username: String,
  email: String,
  password: String
)