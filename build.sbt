import sbtassembly.Plugin._
import scala.Some

version := "1"

scalaVersion := "2.10.3"

organization := "org.kpc"

name := "GibberishLogger"

libraryDependencies ++= {
  Seq(
    "org.slf4j" % "slf4j-api" % "1.7.6",
    "ch.qos.logback" % "logback-classic" % "1.0.9",
    "org.slf4j" % "log4j-over-slf4j" % "1.7.6",
    "com.typesafe" %% "scalalogging-slf4j" % "1.1.0",
    "com.typesafe.akka" %% "akka-actor" % "2.2.3"
  )
}

mainClass := Some("org.kpc.giblog.GibberishLogger")

resolvers ++= Seq("snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases" at "http://oss.sonatype.org/content/repositories/releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

scalacOptions ++= Seq("-unchecked", "-deprecation")

assemblySettings