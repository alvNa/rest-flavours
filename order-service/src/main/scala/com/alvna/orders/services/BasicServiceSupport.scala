package com.alvna.orders.services

import scala.concurrent.Future


trait BasicServiceSupport[T, ID] {

  def add(entity: T): Future[Boolean]

  def get(id: ID): Future[Option[T]]

  def update(entity: T): Future[Boolean]

  def delete(id: ID): Future[Boolean]
}
