package com.alvna.orders.services

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.h2.OrderDAO

import scala.concurrent.Future

class OrderService {

  private[services] val orderDAO = new OrderDAO

  def add(order: Order): Future[String] = ???

  def get(id: String): Future[Option[Order]] = {
    orderDAO.get(id)
  }

  def update(order: Order): Future[Order] = ???

  def delete(id: String): Future[Unit] = ???
}