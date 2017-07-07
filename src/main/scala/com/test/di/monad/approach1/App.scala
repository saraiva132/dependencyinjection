package com.test.di.monad.approach1

import cats.data.Reader

/**
  * READER MONAD DOES NOT DEAL WELL WITH NESTED DEPENDENCIES
  * ASSUMING THE STRUCTURES ARE CLASSES, OF COURSE.
  */
object Env {

  private val persistence = new ActualPersistence
  private val service = new ActualService

  def getPersistence = Repository.mkPersistence(persistence)
  def getService = Repository.mkService(service)

}
object Server {

  import Env._

  //Nested inversion of control (get persistence, get service..)
  def run( program : Reader[Service, Reader[Persistence, MyHttpResponse]]) : MyHttpResponse = {
    getPersistence(getService(program))
  }

  def start(): Unit = {
    //Initialize Route
    val route = new Route
    //Lets generate a program
    val program = route.routes("put")

    val result = run(program)

    println(result)
  }

}

object App extends App {

  Server.start()
}