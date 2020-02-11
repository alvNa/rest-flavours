package com.alvna.orders.common

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.alvna.orders.model.OrdersModel.Order
import spray.json.DefaultJsonProtocol


trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val orderFormat = jsonFormat2(Order)
}