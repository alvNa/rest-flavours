package com.alvna.orders.config

import com.typesafe.config.ConfigFactory

trait DbConfig {

  lazy val configDB = ConfigFactory.defaultApplication.getConfig("db.h2")

  // JDBC driver name and database URL
  val JdbcDriver = configDB.getString("driver")
  val DbUrl = configDB.getString("url")

  // Database credentials
  val User = configDB.getString("user")
  val Pass = configDB.getString("password")
}
