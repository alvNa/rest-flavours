package com.alvna.orders.services

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.h2.OrderDAO
import org.mockito.Matchers.any
import org.mockito.Mockito.when
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future

class OrderServiceTest extends FeatureSpec with GivenWhenThen with Matchers
  with MockitoSugar with ScalaFutures {

  private val orderDAO = mock[OrderDAO]
  private val orderService = new OrderService(orderDAO)

  feature("The Order Service happy path") {
    scenario("adding a new entity") {

      Given("a valid endpoint")
      val newOrder = Order("1", "i-phone")
      when(orderDAO.add(any)).thenReturn(Future.successful(true))

      When("the orderService is called")
      val resultFuture = orderService.add(newOrder)

      Then("return a valid response")
      whenReady(resultFuture) { result =>
        assert(result)
      }
    }
  }
}
