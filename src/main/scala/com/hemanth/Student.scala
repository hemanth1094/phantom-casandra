package com.hemanth


import com.outworkers.phantom.dsl._

abstract class Students extends Table[Students, Student] with RootConnector{

  override def tableName: String = "students"

  object id extends IntColumn with PartitionKey

  object name extends StringColumn with Index

  object bio extends StringColumn {
    override val name = "biography"
  }

  def add(student: Student) = {
    insert().value(_.id, student.id)
      .value(_.name, student.name)
      .value(_.bio, student.bio)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future()
  }

  def getById(id: Int) = {
    select.where(_.id eqs id).one()
  }

  def getByName(name: String) = {
    select.where(_.name eqs name).one()
  }
}

case class Student(id: Int, name: String, bio: String)
