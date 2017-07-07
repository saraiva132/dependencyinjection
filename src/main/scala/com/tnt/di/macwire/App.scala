package com.tnt.di.macwire
import com.tnt.di.macwire.Structures._

/**
  * Instead of implicits macwire uses MACRO to reduce DI boilerplate. Very interesting and clean but some might
  * say that it might be hard to actually understand what depends on what.
  *
  */
object Server {

  import com.softwaremill.macwire._

  lazy val persistence = wire[Persistence]
  lazy val marshaller = wire[Marshaller]
  lazy val io = wire[IO]

  lazy val orderService = wire[OrderService]
  lazy val quoteService = wire[QuoteService]
  lazy val priceForOptionsService = wire[PriceForOptionsService]
  lazy val invoiceService =  wire[InvoiceService]
  lazy val revenueService = wire[RevenueService]
  lazy val paymentConfirmationService = wire[PaymentConfirmationService]

  lazy val orderRoute = wire[OrderRoute]
  lazy val quoteRoute = wire[QuoteRoute]
  lazy val priceForOptionsRoute = wire[PriceForOptionsRoute]
  lazy val paymentConfirmationRoute = wire[PaymentConfirmationRoute]

  lazy val handler = wire[HTTPHandler]

  def start(): Unit = {
    //Initialize DB, bind handler to HTTP server and start it
    println(handler)
  }

}

object App extends App{

  Server.start()
}
