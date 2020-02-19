package com.alvna.orders.persistence.mapping

import com.alvna.orders.model.OrdersModel.Order
import slick.jdbc.H2Profile.api._

object OrderTable {
  val orders = TableQuery[OrderTable]
}

class OrderTable(tag: Tag) extends Table[Order](tag, /*Some("public"),*/ "orders") {

  def id = column[String]("id", O.PrimaryKey)

  def desc = column[String]("description")

  def * = (id, desc) <> (Order.tupled, Order.unapply)
}

