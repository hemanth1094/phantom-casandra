name := "phantom-cassandra"

version := "0.1"

scalaVersion := "2.11.11"


libraryDependencies ++= Seq(
  "com.outworkers"  %%  "phantom-dsl" % "2.24.10",
  "org.scala-lang" % "scala-reflect" % "2.11.11",
  "com.typesafe" % "config" % "1.3.1"
)