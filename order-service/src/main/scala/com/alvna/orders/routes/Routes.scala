package com.alvna.orders.routes

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

object Routes extends OrderRoute with HealthCheckRoute {

  val VersionV1 = "v1"

  def allRoutesUnified: Route = pathPrefix(VersionV1) {
    orderRoutes ~ healthCheckRoutes
  }

}