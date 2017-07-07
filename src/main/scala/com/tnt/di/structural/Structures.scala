package com.tnt.di.structural

/**
  * Created by rafaelfigueiredo on 01/07/2017.
  */
object Structures {

   class Persistence()
   class Marshaller()

   class IO( env : {
    val persistence : Persistence
    val marshaller : Marshaller
  })

   class OrderService( env : {
    val io : IO
  })

   class QuoteService( env : {
    val io : IO
  })

   class PriceForOptionsService( env : {
    val io : IO
  })

   class InvoiceService()
   class RevenueService()

   class PaymentConfirmationService(env : {
    val invoiceService: InvoiceService
    val revenueService: RevenueService
  })

   class OrderRoute( env : {
    val orderService: OrderService
  })

   class QuoteRoute(env : {
    val quoteService: QuoteService
  })

   class PriceForOptionsRoute(env : {
    val priceForOptionsService: PriceForOptionsService
  })

   class PaymentConfirmationRoute(env : {
    val paymentConfirmationService: PaymentConfirmationService
  })

   class HTTPHandler(env : {
    val orderRoute: OrderRoute
    val quoteRoute: QuoteRoute
    val priceForOptionsRoute: PriceForOptionsRoute
    val paymentConfirmationRoute: PaymentConfirmationRoute
  })

}
