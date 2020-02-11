package com.alvna.orders.routes

import akka.http.scaladsl.model.StatusCodes.{InternalServerError, OK}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.alvna.orders.common.JsonSupport
import com.alvna.orders.services.OrderService

import scala.util.{Failure, Success}

trait OrderRoute extends JsonSupport {
  private[routes] val OrdersPath = "orders"
  private[routes] val orderService = new OrderService()

  def orderRoutes: Route = pathPrefix(OrdersPath) {
    path(Segment) { id =>
      get {
          onComplete(orderService.get(id)) {
              case Success(order) => complete(OK, order)
              case Failure(ex) => complete(InternalServerError, ex.getMessage)
          }
      }
    }
  }
}
