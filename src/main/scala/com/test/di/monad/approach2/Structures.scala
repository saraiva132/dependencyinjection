package com.test.di.monad.approach2

import cats.data.Reader

case class MyHttpResponse(result: String, value: String = "")

trait IntPersistence {
  def get(s: String): Int = 5

  def put(a: Int): Unit = println(s"Inserting $a")

  def delete(s: String): Unit = println(s"Deleting $s")
}

trait Marshaller {
  def marshall( a : Int) : String = a.toString
  def unmarshall(s : String ) : Int = s.toInt
}

case class Config(persistence: IntPersistence, marshaller: Marshaller)

object Service {

  val p = Reader[IntPersistence, IntPersistence](identity)
  val m = Reader[Marshaller, Marshaller](identity)

  def getFromDB(s: String): Reader[IntPersistence, Int] = p map (_.get(s))

  def putInDB(s: Int): Reader[IntPersistence, Unit] = p map (_.put(s))

  def deleteFromDB(s: String): Reader[IntPersistence, Unit] = p map (_.delete(s))

  def marshall( s : Int) : Reader[Marshaller, String] = m map (_.marshall(s))
  def unmarshall( s : String) : Reader[Marshaller, Int] = m map (_.unmarshall(s))

}

object Route {

  def routes(path: String, param: String = "50"): Reader[Config, MyHttpResponse] = {
    path match {
      case "get" => for {
        fromDb <- Service.getFromDB(param).local[Config](_.persistence)
        result <- Service.marshall(fromDb).local[Config](_.marshaller)
      } yield MyHttpResponse("OK", result)
      case "put" => for {
        p <- Service.unmarshall(param).local[Config](_.marshaller)
        _ <- Service.putInDB(p).local[Config](_.persistence)
      } yield MyHttpResponse("OK")
      case "delete" => for {
        _ <- Service.deleteFromDB(param).local[Config](_.persistence)
      } yield MyHttpResponse("OK")
    }
  }
}