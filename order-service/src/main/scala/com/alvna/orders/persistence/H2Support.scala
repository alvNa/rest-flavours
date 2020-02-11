package com.alvna.orders.persistence

import com.typesafe.config.ConfigFactory
import slick.jdbc.JdbcBackend.Database

class H2Support {
  lazy val config = ConfigFactory.load()
  lazy val db  = Database.forConfig("h2", config)
}
