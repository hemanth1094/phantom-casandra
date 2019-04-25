package com.hemanth.cassandra

import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

object ConfigurationHelper {


  lazy private val config =
    ConfigFactory.load()
      .withFallback(ConfigFactory.load("platform"))

  def getString(path: String): String =
    config.getString(path)

  def getInt(path: String): Int =
    config.getInt(path)


  def getList(path: String): List[String] =
    config.getStringList(path).asScala.toList

  def getDouble(path: String): Double =
    config.getDouble(path)


}