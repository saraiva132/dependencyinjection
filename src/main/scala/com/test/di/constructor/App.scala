package com.test.di.constructor


/**
  * The easiest and more direct way of doing DI. Constructor parameters!! Everyone understand this and it may
  * be the easiest way to do DI and also does not have too many gotchas
  */
object Server {

  import Structures._

  val persistence = new Persistence()
  val marshaller = new Marshaller()
  val io = new IO(persistence,marshaller)

  val orderService = new OrderService(io)
  val quoteService = new QuoteService(io)
  val priceForOptionsService = new PriceForOptionsService(io)
  val invoiceService = new InvoiceService()
  val revenueService = new RevenueService()
  val paymentConfirmationService = new PaymentConfirmationService(invoiceService,revenueService)

  val orderRoute = new OrderRoute(orderService)
  val quoteRoute = new QuoteRoute(quoteService)
  val priceForOptionsRoute = new PriceForOptionsRoute(priceForOptionsService)
  val paymentConfirmationRoute = new PaymentConfirmationRoute(paymentConfirmationService)

  val handler = new HTTPHandler(orderRoute,quoteRoute,priceForOptionsRoute,paymentConfirmationRoute)

  def start(): Unit = {
    //Initialize DB, bind handler to HTTP server and start it
    println(handler)
  }

}

object App extends App {

 Server.start()
}
