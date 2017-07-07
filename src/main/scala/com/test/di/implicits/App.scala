package com.test.di.implicits

/**
  * Same as constructor parameters but use implicits to reduce boiler plate. I believe this might be a
  * bit confusing sometimes. It is dangerous to over-use implicits
  */
object Server {

  import Structures._

  implicit val persistence = new Persistence()
  implicit val marshaller = new Marshaller()
  implicit val io = new IO()

  implicit val orderService = new OrderService()
  implicit val quoteService = new QuoteService()
  implicit val priceForOptionsService = new PriceForOptionsService()
  implicit val invoiceService = new InvoiceService()
  implicit val revenueService = new RevenueService()
  implicit val paymentConfirmationService = new PaymentConfirmationService()

  implicit val orderRoute = new OrderRoute()
  implicit val quoteRoute = new QuoteRoute()
  implicit val priceForOptionsRoute = new PriceForOptionsRoute()
  implicit val paymentConfirmationRoute = new PaymentConfirmationRoute()

  val handler = new HTTPHandler()

  def start(): Unit = {
    //Initialize DB, bind handler to HTTP server and start it
    handler.print
    println(handler)
  }

}

object App extends App{

  Server.start()
}
