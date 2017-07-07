package com.test.di.thincake

import com.test.di.thincake.Structures._

/**
  * An extremely clean way of doing DI although, given many dependencies, it might get hairy.
  *
  * See below. Given an HTTPHandler and all those traits. What depends on what?
  */
object Server extends HTTPHandler
  with OrderRoute
  with QuoteRoute
  with PriceForOptionsRoute
  with PaymentConfirmationRoute
  with OrderService
  with QuoteService
  with PriceForOptionsService
  with PaymentConfirmationService
  with RevenueService
  with InvoiceService
  with IO
  with Persistence
  with Marshaller {

  def start(): Unit = {
    //Initialize DB, bind handler to HTTP server and start it
  }

}

object App extends App {

  Server.start()
}
