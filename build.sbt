lazy val catsVersion = "2.10.0"

lazy val root = (project in file("."))
  .settings(
    name := "cats-essentials",
    version := "0.0.1",
    scalaVersion := "3.2.1",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % catsVersion
    )
  )
