package com.tnt.di.cake

import com.tnt.di.cake.Structures.HTTPHandler

object Server {

  val handler = new HTTPHandler
    with ActualQuoteRouteComponent
    with ActualPaymentConfirmationRouteComponent
    with ActualPriceForOptionsRouteComponent
    with ActualOrderRouteComponent

  def start(): Unit = {
    //Initialize DB, bind handler to HTTP server and start it
    println(handler.quoteRoute.quoteService.io.persistence.hello)
  }

}

object App extends App {

  Server.start()
}
