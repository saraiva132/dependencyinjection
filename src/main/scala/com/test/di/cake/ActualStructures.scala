package com.test.di.cake

import com.test.di.cake.Structures._


trait ActualPersistenceComponent extends PersistenceComponent {
  val persistence = new ActualPersistence

  class ActualPersistence extends Persistence {
    def hello = "Defined persistence..."
  }

}

trait ActualMarshallerComponent extends MarshallerComponent {
  val marshaller = new ActualMarshaller

  class ActualMarshaller extends Marshaller

}

trait ActualIOComponent extends IOComponent {
  val io = new ActualIO

  class ActualIO extends IO with ActualPersistenceComponent with ActualMarshallerComponent

}

trait ActualOrderServiceComponent extends OrderServiceComponent {
  val orderService = new ActualOrderService

  class ActualOrderService extends OrderService with ActualIOComponent

}

trait ActualQuoteServiceComponent extends QuoteServiceComponent {
  val quoteService = new ActualQuoteService

  class ActualQuoteService extends QuoteService with ActualIOComponent

}

trait ActualPriceForOptionsServiceComponent extends PriceForOptionsServiceComponent {
  val priceForOptionsService = new ActualPriceForOptionsService

  class ActualPriceForOptionsService extends PriceForOptionsService with ActualIOComponent

}

trait ActualInvoiceServiceComponent extends InvoiceServiceComponent {
  val invoiceService = new ActualInvoiceService

  class ActualInvoiceService extends InvoiceService

}

trait ActualRevenueServiceComponent extends RevenueServiceComponent {
  val revenueService = new ActualRevenueService

  class ActualRevenueService extends RevenueService

}

trait ActualPaymentConfirmationServiceComponent extends PaymentConfirmationServiceComponent {
  val paymentConfirmationService = new ActualPaymentConfirmationService

  class ActualPaymentConfirmationService extends PaymentConfirmationService
    with ActualInvoiceServiceComponent
    with ActualRevenueServiceComponent

}

trait ActualOrderRouteComponent extends OrderRouteComponent {
  val orderRoute = new ActualOrderRoute

  class ActualOrderRoute extends OrderRoute with ActualOrderServiceComponent

}

trait ActualQuoteRouteComponent extends QuoteRouteComponent {
  val quoteRoute = new ActualQuoteRoute

  class ActualQuoteRoute extends QuoteRoute with ActualQuoteServiceComponent

}

trait ActualPriceForOptionsRouteComponent extends PriceForOptionsRouteComponent {
  val priceForOptionsRoute = new ActualPriceForOptionsRoute

  class ActualPriceForOptionsRoute extends PriceForOptionsRoute with ActualPriceForOptionsServiceComponent

}

trait ActualPaymentConfirmationRouteComponent extends PaymentConfirmationRouteComponent {
  val paymentConfirmationRoute = new ActualPaymentConfirmationRoute

  class ActualPaymentConfirmationRoute extends PaymentConfirmationRoute with ActualPaymentConfirmationServiceComponent

}
