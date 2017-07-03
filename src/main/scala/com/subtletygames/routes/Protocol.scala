package com.subtletygames.routes

import com.subtletygames.entities.{Login, Signup}

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait LoginProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat2(Login)
}

trait SignupProtocol extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val format = jsonFormat3(Signup)
}