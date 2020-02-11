package com.alvna.orders.routes

import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{Matchers, WordSpec}

class HealthCheckRouteTest extends WordSpec with Matchers with ScalatestRouteTest with HealthCheckRoute {

    "The HealthCheck Route" should {
      "return a OK for GET requests" in {
        Get(HealthCheckEndpoint) ~> healthCheckRoutes ~> check {
          responseAs[String] shouldEqual "Status UP"
          status shouldEqual OK
        }
      }
    }
}
