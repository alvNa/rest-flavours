package com.alvna.orders

import com.alvna.orders.model.OrdersModel.Order
import com.alvna.orders.persistence.mapping.OrderTable.orders
import slick.jdbc.DB2Profile.api._

import scala.concurrent.Await
import scala.concurrent.duration._

trait PopulateDb {

  val db = Database.forConfig("db.h2")

  val setup = DBIO.seq(
    // Create the tables, including primary and foreign keys.,
    orders.schema.create,

    // Insert data
    orders ++= Seq(
      Order("1", "iphone"),
      Order("2", "android")
    )
  )

  //Populate DB
  val setupFuture = db.run(setup)
  Await.ready(setupFuture, 5.seconds)
}
