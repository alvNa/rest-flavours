package com.alvna.orders.routes

import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.model.StatusCodes.OK
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait HealthCheckRoute {

  val HealthCheckPath = "healthcheck"
  val HealthCheckEndpoint = s"/$HealthCheckPath"

  def healthCheckRoutes: Route = path(HealthCheckPath) {
    get {
      pathEndOrSingleSlash {
        complete(OK, HttpEntity(`application/json`, "Status UP"))
      }
    }
  }
}
