package com.alvna.orders.persistence

import scala.concurrent.Future

/**
 * Basic DAO operations
 **/
trait BasicDAO[T, ID] {

  def add(entity: T): Future[Boolean]

  def get(id: ID): Future[Option[T]]

  def update(entity: T): Future[Boolean]

  def delete(id: ID): Future[Boolean]
}
