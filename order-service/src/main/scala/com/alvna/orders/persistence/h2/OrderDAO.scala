package com.alvna.orders.persistence.h2

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.mapping.OrderTable
import com.alvna.orders.persistence.{BasicDAO, H2Support}
import com.alvna.orders.util.Converters.CustomInt
import slick.jdbc.PostgresProfile.api._
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class OrderDAO extends H2Support with BasicDAO[Order,String] {

  private val orderTable = TableQuery[OrderTable]

  override def add(entity: Order): Future[Boolean] = {
    db.run(orderTable += entity).map(_.toBool)
  }

  override def get(id: String): Future[Option[Order]] = {
    db.run(orderTable.filter(_.id === id).result.headOption)
  }

  override def update(entity: Order): Future[Boolean] = {
    db.run(orderTable.filter(_.id === entity.id).update(entity)).map(_.toBool)
  }

  override def delete(id: String): Future[Boolean] = {
    db.run(orderTable.filter(_.id === id).delete).map(_.toBool)
  }
}