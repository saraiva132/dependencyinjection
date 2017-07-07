package com.tnt.di.monad.approach1

import cats.data.Reader

case class MyHttpResponse(result: String, value: Option[Int] = None)

trait Persistence {
  def get(s: String): Int

  def put(a: Int): Unit

  def delete(s: String): Unit
}

trait Service {
  def getFromDB(s: String): Reader[Persistence, Int]

  def putInDB(s: Int): Reader[Persistence, Unit]

  def deleteFromDB(s: String): Reader[Persistence, Unit]
}


class ActualPersistence extends Persistence {
  override def get(s: String): Int = 5

  override def put(a: Int): Unit = println(s"Inserting $a")

  override def delete(s: String): Unit = println(s"Deleting $s")
}

class ActualService extends Service {

  val p = Reader[Persistence, Persistence](identity)

  type P[A] = Reader[Persistence, A]

  override def getFromDB(s: String): Reader[Persistence, Int] = p map (_.get(s))

  override def putInDB(s: Int): Reader[Persistence, Unit] = p map (_.put(s))

  override def deleteFromDB(s: String): Reader[Persistence, Unit] = p map (_.delete(s))
}

class Route {
  val s = Reader[Service, Service](identity)

  def routes(path: String, param: String = "50"): Reader[Service, Reader[Persistence, MyHttpResponse]] = path match {
    case "get" =>
      s map (_.getFromDB(param) map (value => MyHttpResponse("OK", Some(value))))
    case "put" =>
      s map (_.putInDB(param.toInt) map (_ => MyHttpResponse("OK")))
    case "delete" =>
      s map (_.deleteFromDB(param) map (_ => MyHttpResponse("OK")))
  }
}