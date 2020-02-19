package com.alvna.orders.routes

import akka.http.scaladsl.model.StatusCodes.{InternalServerError, NotFound, OK, NotModified}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.alvna.orders.common.JsonSupport
import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.services.OrderService

import scala.util.{Failure, Success}

trait OrderRoute extends JsonSupport {
  private[routes] val OrdersPath = "orders"
  private[routes] val orderService = new OrderService()

  def orderRoutes: Route = pathPrefix(OrdersPath) {
    pathEndOrSingleSlash {
      post {
        entity(as[Order]) { order =>
          println(order)
          val futureOrder = orderService.add(order)

          onComplete(futureOrder) {
            case Success(updated) => updated match {
              case true =>
                println(2)
                complete(OK,order)
              case false =>
                println(2)
                complete(NotModified)
            }
            case Failure(ex) =>
              println(3)
              complete(InternalServerError, ex.getMessage)
          }
        }
      }
    } ~
    path(Segment) { id =>
      get {
        onComplete(orderService.get(id)) {
          case Success(maybeOrder) => maybeOrder match {
            case Some(order) => complete(OK, order)
            case None => complete(NotFound)
          }
          case Failure(ex) => complete(InternalServerError, ex.getMessage)
        }
      }
    }
  }
}
