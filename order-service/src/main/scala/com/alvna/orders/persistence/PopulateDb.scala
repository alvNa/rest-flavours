package com.alvna.orders.persistence

import com.alvna.orders.config.DbConfig
import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.mapping.OrderTable.orders
import slick.jdbc.DB2Profile.api._
import slick.jdbc.GetResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

trait PopulateDb extends DbConfig {

  val scriptInit = ";INIT=runscript from 'src/main/resources/orders.sql'"

  val db = Database.forURL(url = DbUrl + scriptInit, user = User, password = Pass, driver = JdbcDriver)

  implicit val getOrderResult = GetResult(r => Order(r.<<, r.<<))

  val call = sql"SELECT * FROM orders".as[Order]

  val result = db.run(call)

  result.onComplete {
    case Success(orders) => setup(orders.toList)
      println("DB population OK")
    case Failure(t) =>
      println("DB population KO" + t.getMessage)
  }

  //Populate DB
  private def setup(_orders: List[Order]) = {
    db.run(DBIO.seq(
      // Create the tables, including primary and foreign keys
      orders.schema.create,
      // Insert data
      orders ++= _orders
    ))
  }
}
