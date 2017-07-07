package com.tnt.di.constructor

/**
  * Created by rafaelfigueiredo on 01/07/2017.
  */
object Structures {

  case class Persistence()
  case class Marshaller()
  case class IO(p: Persistence, m: Marshaller)

  case class OrderService(io: IO)
  case class QuoteService(io: IO)
  case class PriceForOptionsService(io: IO)
  case class InvoiceService()
  case class RevenueService()
  case class PaymentConfirmationService(invoice: InvoiceService, revenue: RevenueService)

  case class OrderRoute(os: OrderService)
  case class QuoteRoute(qs: QuoteService)
  case class PriceForOptionsRoute(pfos: PriceForOptionsService)
  case class PaymentConfirmationRoute(pcs: PaymentConfirmationService)

  case class HTTPHandler(or: OrderRoute, qr: QuoteRoute, pfor: PriceForOptionsRoute, pcr: PaymentConfirmationRoute)

}
