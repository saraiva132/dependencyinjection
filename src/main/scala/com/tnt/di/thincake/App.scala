package com.tnt.di.thincake

import com.tnt.di.thincake.Structures._

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
