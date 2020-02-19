package com.alvna.orders

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import com.alvna.orders.persistence.{OrdersDb, OrdersDb2, OrdersDb3, PopulateDb}
import com.alvna.orders.routes.Routes

import scala.util.{Failure, Success}

object Server extends App with PopulateDb
{
  implicit val system = ActorSystem("order-service")
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val log = Logging(system, getClass)

  //OrdersDb3.setup()

  val bindingFuture = Http().bindAndHandle(Routes.allRoutesUnified, "localhost", 8080)

  bindingFuture.onComplete {
      case Success(value) => log.info(s"Server is up. $value")
      case Failure(ex) =>
        log.error(ex, s"Server Failed")
        system.terminate()
    } // and shutdown when done
}