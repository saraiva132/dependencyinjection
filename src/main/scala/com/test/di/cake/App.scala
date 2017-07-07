package com.test.di.cake

import com.test.di.cake.Structures.HTTPHandler

/**
  * Cake pattern. Dependencies are wrapped by a component. Too much boiler plate. Is this even used?
  */
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
