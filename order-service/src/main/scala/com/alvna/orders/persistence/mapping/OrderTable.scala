package com.alvna.orders.persistence.mapping

import com.alvna.orders.model.OrdersModel.Order
import slick.jdbc.H2Profile.api._

class OrderTable(tag: Tag) extends Table[Order](tag, "orders") {

  def id = column[String]("ID", O.PrimaryKey)

  def desc = column[String]("DESC")

  def * = (id, desc) <> (Order.tupled, Order.unapply)
}
