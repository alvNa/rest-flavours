package com.alvna.rest.server

import com.twitter.finagle.http.Request
import com.twitter.finagle.{Service, http}

class CustomerResource {


  val httpService: Service[http.Request, http.Response] = ???

  httpService(Request("/foo/bar")).onSuccess { response: http.Response =>
    println("received response " + response.contentString)
  }
}
