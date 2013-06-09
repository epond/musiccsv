name := "musiccsv"

version := "1.0"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "1.14" % "test",
    "com.github.tototoshi" %% "scala-csv" % "0.8.0",
    "joda-time" % "joda-time" % "2.2",
    "org.joda" % "joda-convert" % "1.3.1"
)