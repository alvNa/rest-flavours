package com.alvna.orders.persistence.h2

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.mapping.OrderTable.orders
import com.alvna.orders.persistence.{BasicDAO, H2Support}
import com.alvna.orders.util.Converters.CustomInt
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class OrderDAO extends H2Support with BasicDAO[Order,String] {

  override def add(entity: Order): Future[Boolean] = {
    db.run(orders += entity).map(_.toBool)
  }

  override def get(id: String): Future[Option[Order]] = {
    db.run(orders.filter(_.id === id).result.headOption)
  }

  override def update(entity: Order): Future[Boolean] = {
    db.run(orders.filter(_.id === entity.id).
      update(entity))
      .map(_.toBool)
  }

  override def delete(id: String): Future[Boolean] = {
    db.run(orders.filter(_.id === id).delete).map(_.toBool)
  }
}