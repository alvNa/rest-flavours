package com.alvna.orders.util

object Converters {

  implicit class CustomInt(n: Int) {

    def toBool: Boolean = n match {
      case 1 => true
      case _ => false
    }
  }

}
