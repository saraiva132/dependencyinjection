package com.test.di.monad.approach2

/**
  * Solved approach1 nested dependencies problem by:
  *   - encoding the classes as functions which makes the code play nicer with Reader
  *   - composing the functions with Reader in a for comprehension and using it
  **/
object Server {

  val config = new Config(new Persistence {})

  def start(): Unit = {
    val program = Route.routes("put", "1")

    val result = program(config)

    println(result)
  }

}

object App extends App {

  Server.start()
}