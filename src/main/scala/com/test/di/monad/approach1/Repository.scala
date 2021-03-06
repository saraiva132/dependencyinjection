package com.test.di.monad.approach1

import cats.data.Reader

object Repository {

  abstract class PersistenceProvider {
    def apply[A](f: Reader[Persistence, A]) : A
  }

  def mkPersistence(persistence : Persistence) : PersistenceProvider = {
    new PersistenceProvider {
      override def apply[A](f: Reader[Persistence, A]): A = {
        f(persistence)
      }
    }
  }

  abstract class ServiceProvider {
    def apply[A](f: Reader[Service, A]) : A
  }

  def mkService(service : Service): ServiceProvider = {
    new ServiceProvider {
      override def apply[A](f: Reader[Service, A]): A = {
        f(service)
      }
    }
  }

}
