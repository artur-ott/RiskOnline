name          := "htwg-scala-seed"
organization  := "de.htwg.se"
version       := "0.0.1"
scalaVersion  := "2.11.8"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")
mainClass := Some("ScalaRisk")

resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  val scalaTestV       = "3.0.0-M15"
  val scalaMockV       = "3.2.2"
  Seq(
    "org.scalatest" %% "scalatest"                   % scalaTestV       % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV       % "test"
  )
}

libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11+"
libraryDependencies += "junit" % "junit" % "4.8" % "test"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"	% "provided"
libraryDependencies += "log4j" % "log4j" % "1.2.17"



import com.github.retronym.SbtOneJar._

oneJarSettings

// or if using sbt version < 0.13
// seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"