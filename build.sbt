lazy val catsVersion = "2.10.0"

lazy val root = (project in file("."))
  .settings(
    name := "cats-essentials",
    version := "0.0.1",
    scalaVersion := "2.13.10",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % catsVersion
    )
  )
