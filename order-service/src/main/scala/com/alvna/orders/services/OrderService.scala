package com.alvna.orders.services

import com.alvna.orders.model.OrdersModel.Order

import scala.concurrent.Future

class OrderService {

  def add(order: Order): Future[String] = ???

  def get(id: String): Future[Order] = ???
  //{
  //  Future.successful(Order("id","1"))
  //}

  def update(order: Order): Future[Order] = ???

  def delete(id: String): Future[Unit] = ???
}