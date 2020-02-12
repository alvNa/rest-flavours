package com.alvna.orders.routes

import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.MediaTypes.`application/json`
import akka.http.scaladsl.model.StatusCodes.{MethodNotAllowed, NotFound, OK}
import akka.http.scaladsl.server._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.services.OrderService
import org.mockito.Matchers.any
import org.mockito.Mockito.when
import org.scalatest.{FeatureSpec, Matchers, _}
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future

class OrderRouteTest extends FeatureSpec with GivenWhenThen
  with OrderRoute with ScalatestRouteTest
  with Matchers with MockitoSugar {

  override val orderService = mock[OrderService]

  private val OrdersEndpoint = s"/${OrdersPath}"
  private val OrderEndpoint = s"/${OrdersPath}/1"

  feature("The Order Route happy path") {
    scenario("return a OK for GET requests") {

      Given("a valid endpoint")
      val currentOrder = Order("1", "i-phone")
      when(orderService.get(any)).thenReturn(Future.successful(Some(currentOrder)))

      When("the endpoint is invoked")
      val response = Get(OrderEndpoint) ~> orderRoutes

      Then("return a valid response")
      response ~> check {
        responseAs[Order] shouldEqual currentOrder
        status shouldEqual OK
      }
    }

    scenario("return a 404 for GET requests") {

      Given("a valid endpoint")
      when(orderService.get(any)).thenReturn(Future.successful(None))

      When("the endpoint is invoked")
      val response = Get(OrderEndpoint) ~> orderRoutes

      Then("return a 404 response")
      response ~> check {
        status shouldEqual NotFound
      }
    }

    scenario("return a OK for POST requests") {
      Given("a valid request to the endpoint")
      val newOrder = Order("1", "i-phone")
      when(orderService.add(any)).thenReturn(Future.successful(true))

      val orderJson = s"""{
         "id": "1",
         "desc": "i-phone"
       }"""

      When("the endpoint is invoked")
      val response = Post(OrdersEndpoint,HttpEntity(`application/json`, orderJson)) ~> Route.seal(orderRoutes)

      Then("return a valid response")
      response ~> check {
        responseAs[Order] shouldEqual newOrder
        status shouldEqual OK
      }
    }
  }

  feature("The Order Route angry path") {
    scenario("return a 403 for PUT requests") {

      Given("a not implemented endpoint")
      val currentOrder = Order("1", "i-phone")
      when(orderService.get(any)).thenReturn(Future.successful(Some(currentOrder)))

      When("the endpoint is invoked")
      val response = Put(OrderEndpoint) ~> Route.seal(orderRoutes)

      Then("return a 403 response")
      response ~> check {
        status shouldEqual MethodNotAllowed
      }
    }
  }
}
