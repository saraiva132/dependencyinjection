package com.test.di.monad.approach2

import cats.data.Reader

case class MyHttpResponse(result: String, value: Option[Int] = None)

trait Persistence {
  def get(s: String): Int = 5

  def put(a: Int): Unit = println(s"Inserting $a")

  def delete(s: String): Unit = println(s"Deleting $s")
}

case class Config(persistence: Persistence)

object Service {

  val p = Reader[Persistence, Persistence](identity)

  def getFromDB(s: String): Reader[Persistence, Int] = p map (_.get(s))

  def putInDB(s: Int): Reader[Persistence, Unit] = p map (_.put(s))

  def deleteFromDB(s: String): Reader[Persistence, Unit] = p map (_.delete(s))
}

object Route {

  def routes(path: String, param: String = "50"): Reader[Config, MyHttpResponse] = {
    path match {
      case "get" => for {
        fromDb <- Service.getFromDB(param).local[Config](_.persistence)
      } yield MyHttpResponse("OK", Some(fromDb))
      case "put" => for {
        _ <- Service.putInDB(param.toInt).local[Config](_.persistence)
      } yield MyHttpResponse("OK", None)
      case "delete" => for {
        _ <- Service.deleteFromDB(param).local[Config](_.persistence)
      } yield MyHttpResponse("OK", None)
    }
  }
}