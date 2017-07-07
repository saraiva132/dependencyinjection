package com.tnt.di.structural

import Structures._

object Backend {
   val persistence = new Persistence()
   val marshaller = new Marshaller()
}

object IO {
  val io = new IO(Backend)
}

object Services {
  val orderService = new OrderService(IO)
  val quoteService = new QuoteService(IO)
  val priceForOptionsService = new PriceForOptionsService(IO)
  object MoreServices {
    val invoiceService = new InvoiceService()
    val revenueService = new RevenueService()
  }
  val paymentConfirmationService = new PaymentConfirmationService(MoreServices)
}

object Routes {
  val orderRoute = new OrderRoute(Services)
  val quoteRoute = new QuoteRoute(Services)
  val priceForOptionsRoute = new PriceForOptionsRoute(Services)
  val paymentConfirmationRoute = new PaymentConfirmationRoute(Services)
}

object Server {
  val handler = new HTTPHandler(Routes)

  def start(): Unit = {
    //Initialize DB, bind handler to HTTP server and start it
    println(handler)
  }

}

object App extends App {

 Server.start()
}
