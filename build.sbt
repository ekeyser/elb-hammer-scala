name := "elb-hammer"

version := "1.0"

scalaVersion := "2.12.1"

organization := "com.cloudywaters"

libraryDependencies ++= Seq(
  "org.json4s" %% "json4s-native" % "3.5.0",
  "com.amazonaws" % "aws-java-sdk" % "1.11.97"
)
