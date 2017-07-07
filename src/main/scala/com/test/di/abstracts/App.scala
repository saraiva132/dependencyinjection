package com.test.di.abstracts

/**
  * Use abstract dependencies. Traits should depend upon abstractions (i.e. def dependency : AbstractTrait)
  *
  * Instantiate concrete implementations afterwards.
  */
object Server {

  import Structures._

  val _io = new IO {
    val persistence: Persistence = new Persistence {}
    val marshaller: Marshaller = new Marshaller {}
  }

  val _orderService = new OrderService {
    val io: IO = _io
  }

  val _quoteService = new QuoteService {
    val io: IO = _io
  }

    val _priceForOptionsService = new PriceForOptionsService {
     val io: IO = _io
    }

    val _paymentConfirmationService = new PaymentConfirmationService {
      val revenue: RevenueService = new RevenueService {}
      val invoice: InvoiceService = new InvoiceService {}
    }


  val _orderRoute = new OrderRoute {
    val orderService: OrderService = _orderService
  }

  val _quoteRoute = new QuoteRoute {
    val quoteService: QuoteService = _quoteService
  }

  val _priceForOptionsRoute = new PriceForOptionsRoute {
   val priceForOptionsService: PriceForOptionsService = _priceForOptionsService
  }

  val _paymentConfirmationRoute = new PaymentConfirmationRoute {
    val paymentConfirmationService : PaymentConfirmationService = _paymentConfirmationService
  }

  val handler = new HTTPHandler {
    override val orderRoute: OrderRoute = _orderRoute
    override val quoteRoute: QuoteRoute = _quoteRoute
    override val priceForOptionsRoute: PriceForOptionsRoute = _priceForOptionsRoute
    override val paymentConfirmationRoute: PaymentConfirmationRoute = _paymentConfirmationRoute
  }

  def start(): Unit = {
    //Initialize DB, bind handler to HTTP server and start it
    println(handler)
  }

}

object App extends App{

  Server.start()
}
