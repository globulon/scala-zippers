import sbt._
import sbt.classpath._
import Keys._
import Process._
import System._

object BuildSettings {
  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization        := "com.promindis",
    version             := "0.1-SNAPSHOT",
    scalaVersion        := "2.9.1",
    scalacOptions       := Seq("-unchecked", "-deprecation")
  )
}


object Resolvers {
  val typesafeReleases = "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
  val scalaToolsReleases = "Scala-Tools Maven2 Releases Repository" at "http://scala-tools.org/repo-releases"
  val scalaToolsSnapshots = "Scala-Tools Maven2 Snapshots Repository" at "http://scala-tools.org/repo-snapshots"
}

object TestDependencies {
  val specs2Version = "1.6.1"
  val testDependencies = "org.specs2" %% "specs2" % specs2Version % "test"
}

object MainBuild extends Build {
  import Resolvers._
  import TestDependencies._
  import BuildSettings._

	lazy val algorithms = Project(
		"zippers",
		file("."),
		settings = buildSettings ++ Seq(resolvers += typesafeReleases) ++  
              Seq (libraryDependencies ++= Seq(testDependencies))
	)

}