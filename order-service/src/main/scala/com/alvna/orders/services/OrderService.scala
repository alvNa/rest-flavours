package com.alvna.orders.services

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.h2.OrderDAO

import scala.concurrent.Future

class OrderService(orderDAO:OrderDAO = new OrderDAO) extends BasicServiceSupport[Order,String]{

  def add(order: Order): Future[Boolean] = {
    orderDAO.add(order)
  }

  def get(id: String): Future[Option[Order]] = {
    orderDAO.get(id)
  }

  def update(order: Order): Future[Boolean] = {
    orderDAO.update(order)
  }

  def delete(id: String): Future[Boolean] = {
    orderDAO.delete(id)
  }
}