package com.hemanth.cassandra

import com.datastax.driver.core.Session

import scala.collection.JavaConverters._

trait CassandraRepository {

  val db: String
  val table: String

  def getSession: Session

  def getDocument(brandId: Int, brandVersion: Int, userId: Int, docId: String): Option[String] = {
    val result = getSession.execute(
      s"""SELECT * FROM $db.$table WHERE brand_id = $brandId AND brand_version = $brandVersion AND user_id = $userId
         | AND id = '$docId' ALLOW FILTERING;""".stripMargin)
    result.asScala.map{ row => row.getString("content_cleaned")}.headOption
  }

  //cassandra does not support delete/update using index
  def deleteByBrandIdAndVersion(brandId: Int, brandVersion: Int, userId: Int): Boolean = {
    val result = getSession.execute(s"""DELETE FROM $db.$table WHERE brand_id = $brandId AND brand_version = $brandVersion AND user_id = $userId;""")
    result.wasApplied()
  }

  def getCount(brandId : Int, brandVersion : Int, userId : Int): Option[Long] ={
    val result = getSession.execute(
      s"""SELECT COUNT(*) as count From $db.$table WHERE brand_id = $brandId AND user_id = $userId AND brand_version = $brandVersion ALLOW FILTERING;""".stripMargin)
    result.asScala.map{ row => row.getLong("count")}.headOption
  }
}

object CassandraRepository extends CassandraRepository {
  val db: String = ConfigurationHelper.getString( "cassandra.keyspace")
  val table: String = ConfigurationHelper.getString("cassandra.score.table")
  def getSession: Session = Connector.session
}
