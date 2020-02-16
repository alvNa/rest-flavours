package com.alvna.orders.persistence

import slick.jdbc.H2Profile.api._

class H2Support {
  lazy val db = Database.forConfig("db.h2")
}
