package com.tnt.di.abstracts

object Structures {

  trait Persistence
  trait Marshaller

  trait IO {
    def persistence : Persistence
    def marshaller : Marshaller
  }

  trait OrderService {
    def io : IO
  }
  trait QuoteService {
    def io : IO
  }
  trait PriceForOptionsService {
    def io : IO
  }

  trait InvoiceService
  trait RevenueService

  trait PaymentConfirmationService {
    def invoice: InvoiceService
    def revenue: RevenueService
  }

  trait OrderRoute {
    def orderService : OrderService
  }

  trait QuoteRoute {
    def quoteService : QuoteService
  }

  trait PriceForOptionsRoute{
    def priceForOptionsService : PriceForOptionsService
  }

  trait PaymentConfirmationRoute {
    def paymentConfirmationService : PaymentConfirmationService
  }

  trait HTTPHandler {
    def orderRoute : OrderRoute

    def quoteRoute : QuoteRoute

    def priceForOptionsRoute: PriceForOptionsRoute

    def paymentConfirmationRoute : PaymentConfirmationRoute
  }

}
