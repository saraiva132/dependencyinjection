package com.tnt.di.cake

object Structures {

  trait PersistenceComponent {
    val persistence: Persistence

    trait Persistence {
      def hello: String
    }

  }

  trait MarshallerComponent {
    val marshaller: Marshaller

    trait Marshaller

  }

  trait IOComponent {
    val io: IO

    trait IO {
      self: PersistenceComponent with MarshallerComponent =>
    }

  }

  trait OrderServiceComponent {
    val orderService: OrderService

    trait OrderService {
      self: IOComponent =>
    }

  }

  trait QuoteServiceComponent {
    val quoteService: QuoteService

    trait QuoteService {
      self: IOComponent =>
    }

  }

  trait PriceForOptionsServiceComponent {
    val priceForOptionsService: PriceForOptionsService

    trait PriceForOptionsService {
      self: IOComponent =>
    }

  }

  trait InvoiceServiceComponent {
    val invoiceService: InvoiceService

    trait InvoiceService

  }

  trait RevenueServiceComponent {
    val revenueService: RevenueService

    trait RevenueService

  }


  trait PaymentConfirmationServiceComponent {
    val paymentConfirmationService: PaymentConfirmationService

    trait PaymentConfirmationService {
      self: InvoiceServiceComponent with RevenueServiceComponent =>
    }

  }

  trait OrderRouteComponent {
    val orderRoute: OrderRoute

    trait OrderRoute {
      self: OrderServiceComponent =>
    }

  }

  trait QuoteRouteComponent {
    val quoteRoute: QuoteRoute

    trait QuoteRoute {
      self: QuoteServiceComponent =>
    }

  }

  trait PriceForOptionsRouteComponent {
    val priceForOptionsRoute: PriceForOptionsRoute

    trait PriceForOptionsRoute {
      self: PriceForOptionsServiceComponent =>
    }

  }

  trait PaymentConfirmationRouteComponent {
    val paymentConfirmationRoute: PaymentConfirmationRoute

    trait PaymentConfirmationRoute {
      self: PaymentConfirmationServiceComponent =>
    }

  }

  trait HTTPHandler {
    self: OrderRouteComponent with QuoteRouteComponent with PriceForOptionsRouteComponent with PaymentConfirmationRouteComponent =>
  }

}
