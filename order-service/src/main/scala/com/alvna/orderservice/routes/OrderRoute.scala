package com.alvna.orderservice.routes

import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait OrderRoute {
  val VersionV1 = "v1"
  val OrdersPath = "orders"
  val OrdersEndpoint = s"/$VersionV1/$OrdersPath"

  val getRoute: Route = pathPrefix(VersionV1) {
    path(OrdersPath) {
      get {
        pathEndOrSingleSlash {
          complete(OK, HttpEntity(`application/json`, "Orders OK"))
        }
      }
    }
  }
}
