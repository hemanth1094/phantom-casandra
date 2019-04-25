package com.hemanth

import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._

import scala.io.Source

class DataBaseConnector(override val connector: CassandraConnection) extends Database[DataBaseConnector](connector) {

  object Students extends Students with Connector

}

object Database extends DataBaseConnector(Config.connector) with App {
  val t = Source.fromFile("/home/hemanth/Downloads/43hasn0t0lihm6ofj3d19pm3kp3hjh8739vurko1").getLines().mkString(" ")
  println("start..")
  //Students.add(Student(3, "hemanth", t)).onComplete(_ =>
    Students.getById(3).onComplete(data => println(data.getOrElse("nothing")))
  //)
  shutdown()
}