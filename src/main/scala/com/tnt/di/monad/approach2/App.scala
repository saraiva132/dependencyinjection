package com.tnt.di.monad.approach2

import cats.data.Reader

/**
  * Solved approach1 nested dependencies problem by:
  *   - encoding the classes as functions which makes the code play nicer with Reader
  *   - composing the functions with Reader in a for comprehension and using it
  **/
object Env {

  private val persistence = new ActualPersistence
  private val service = new ActualService

  def getPersistence = Repository.mkPersistence(persistence)
  def getService = Repository.mkService(service)

}
object Server {

  import Env._

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