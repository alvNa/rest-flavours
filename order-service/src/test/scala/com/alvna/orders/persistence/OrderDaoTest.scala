package com.alvna.orders.persistence

import com.alvna.orders.PopulateDb
import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.h2.OrderDAO
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.duration._

class OrderDaoTest extends FeatureSpec with GivenWhenThen with Matchers
  with MockitoSugar with ScalaFutures
  with PopulateDb {

implicit val timeout = 3.seconds

  private val orderDAO = new OrderDAO()

  feature("OrderDAO happy path") {
    scenario("Add a new entity") {
      Given("a order")
      val newOrder = Order("3", "i-pad")

      When("the order is added")
      val futureResult = orderDAO.add(newOrder)

      Then("is inserted in the table")
      whenReady(futureResult) {
        _ shouldBe true
      }
    }

    scenario("Get a existent entity") {
      Given("a valid id")
      val orderId = "1"

      When("the order is get")
      val futureResult = orderDAO.get(orderId)

      Then("entity is retrieved")
      whenReady(futureResult) { maybeOrder =>
        assert(maybeOrder.isDefined)
        maybeOrder.get.id shouldEqual orderId
        assert(maybeOrder.get.desc.nonEmpty)
      }
    }

    scenario("Update a existent entity") {
      Given("a valid order")
      val order = Order("1", "i-pad-2")

      When("the order is updated")
      val futureResult = orderDAO.update(order)

      Then("is updated in the table")
      whenReady(futureResult) {
        _ shouldBe true
      }
    }

    scenario("Delete a existent entity") {
      Given("a valid id")

      When("the order is deleted")
      val futureResult = orderDAO.delete("2")

      Then("is removed from the table")
      whenReady(futureResult) {
        _ shouldBe true
      }
    }
  }
}
