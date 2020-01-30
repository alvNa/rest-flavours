package com.alvna.orderservice.routes

import akka.http.scaladsl.model.StatusCodes.{MethodNotAllowed, OK}
import akka.http.scaladsl.server._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class OrderRouteTest extends WordSpec with Matchers with ScalatestRouteTest with OrderRoute {

  "The Order Route" should {
    "return a OK for GET requests" in {
      Get(OrdersEndpoint) ~> getRoute ~> check {
        responseAs[String] shouldEqual "Orders OK"
        status shouldEqual OK
      }
    }

    "return a MethodNotAllowed error for PUT requests" in {
      Put(OrdersEndpoint) ~> Route.seal(getRoute) ~> check {
        status shouldEqual MethodNotAllowed
      }
    }
  }
}
