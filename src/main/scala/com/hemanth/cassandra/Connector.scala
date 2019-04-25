package com.hemanth.cassandra

import com.datastax.driver.core.{Cluster, Session}

object Connector {

  val host = ConfigurationHelper.getString("cassandra.host").split(",")
  val port = ConfigurationHelper.getString("cassandra.port")

  val cluster: Cluster = Cluster.builder
    .addContactPoints(host: _*)
    .withPort(port.toInt)
    .build
  val session: Session = cluster.connect()

}
