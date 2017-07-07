package com.test.di.thincake

object Structures {

  trait Persistence
  trait Marshaller

  trait IO { self : Persistence with Marshaller =>}
  trait OrderService { self : IO =>}
  trait QuoteService { self : IO =>}
  trait PriceForOptionsService { self : IO =>}

  trait InvoiceService
  trait RevenueService

  trait PaymentConfirmationService { self : InvoiceService with RevenueService => }
  trait OrderRoute { self : OrderService => }
  trait QuoteRoute { self : QuoteService => }

  trait PriceForOptionsRoute { self : PriceForOptionsService =>}
  trait PaymentConfirmationRoute { self : PaymentConfirmationService =>}


  trait HTTPHandler { self : OrderRoute with QuoteRoute with PriceForOptionsRoute with PaymentConfirmationRoute =>}

}
