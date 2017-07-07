package com.test.di.implicits

/**
  * Created by rafaelfigueiredo on 01/07/2017.
  */
object Structures {

  case class Persistence() {
    def print = println("I was instantiated and exist")
  }
  case class Marshaller()
  case class IO()(implicit p: Persistence, m: Marshaller) {
    def print = p.print
  }

  case class OrderService()(implicit io: IO) {
    def print = io.print
  }
  case class QuoteService()(implicit io: IO)
  case class PriceForOptionsService()(implicit io: IO)
  case class InvoiceService()
  case class RevenueService()
  case class PaymentConfirmationService()(implicit invoice: InvoiceService, revenue: RevenueService)

  case class OrderRoute()(implicit os: OrderService) {
    def print = os.print
  }
  case class QuoteRoute()(implicit qs: QuoteService)
  case class PriceForOptionsRoute()(implicit pfos: PriceForOptionsService)
  case class PaymentConfirmationRoute()(implicit pcs: PaymentConfirmationService)

  case class HTTPHandler()(implicit or: OrderRoute, qr: QuoteRoute, pfor: PriceForOptionsRoute, pcr: PaymentConfirmationRoute) {
    def print = or.print
  }

}
