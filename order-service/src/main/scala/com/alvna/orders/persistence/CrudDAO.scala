package com.alvna.orders.persistence

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.mapping.OrderTable
import com.alvna.orders.persistence.mapping.OrderTable.orders
import slick.jdbc.JdbcBackend.Database
import slick.lifted.{TableQuery, _}

import scala.concurrent.Future
import com.alvna.orders.util.Converters.CustomInt
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import slick.lifted._

//abstract class CrudDAO[T,ID](implicit db:Database, val profile: JdbcProfile, table:TableQuery[T]){
//
//  import profile.api._
//
//  def add(entity: T): Future[Boolean] = {
//    db.run(aggregate(table,entity))
//  }
//
////  override def get(id: ID): Future[Option[Order]] = {
////    db.run(table.filter(x => compare(getId(x),id)).result.headOption)
////  }
//
//  def aggregate(table:slick.lifted.TableQuery[T], entity: T):DBIOAction[Boolean, NoStream, Nothing]
//
//  def getId(entity: T): ID
//
//  def compare(id1: ID, id2: ID):T
//}
