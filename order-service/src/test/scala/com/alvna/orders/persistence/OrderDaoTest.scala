package com.alvna.orders.persistence

import java.sql.{Connection, DriverManager}

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.h2.OrderDAO
import com.alvna.orders.services.OrderService
import org.mockito.Matchers.any
import org.mockito.Mockito.when
import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}
import org.scalatest.concurrent.ScalaFutures
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future
//import java.sql.Connection
//import java.sql.DriverManager
import slick.jdbc.JdbcBackend.{Database => SlickDatabase, Session}

class OrderDaoTest extends FeatureSpec with GivenWhenThen with Matchers
  with MockitoSugar with ScalaFutures {

  val conn = SlickDatabase.forURL("jdbc:h2:mem:test", driver = "org.h2.Driver")
  //val conn: Connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "")

/*
  conn.withSession {
    import scala.slick.session.Database.threadLocalSession
    val t = MyTable
    t.ddl.create
  }
*/

  //Class.forName("org.h2.Driver")


  private val orderDAO = new OrderDAO

  feature("The Order Service happy path") {
    scenario("adding a new entity") {

      Given("a valid endpoint")
      val newOrder = Order("1", "i-phone")
      //when(orderDAO.add(any)).thenReturn(Future.successful(true))

      When("the orderService is called")
      val resultFuture = orderDAO.add(newOrder)

      Then("return a valid response")
      whenReady(resultFuture) { result =>
        assert(result)
      }
    }
  }
}
