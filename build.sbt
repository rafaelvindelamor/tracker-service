val appName = "tracker-service"

ThisBuild / organization := "io.rva"
ThisBuild / version      := "0.1"
ThisBuild / scalaVersion := "2.13.5"

lazy val commonSettings = Seq(
  test in assembly := {}
)

lazy val app = (project in file("."))
  .dependsOn(domain, persistence, web)

lazy val domain = module("domain")

lazy val persistence = module("persistence")
  .dependsOn(domain)

lazy val web = module("web")
  .dependsOn(domain)

lazy val e2e = module("e2e")
  .dependsOn(app)

def module(module: String): Project =
  Project(s"$appName-$module", file(module))
    .settings(commonSettings)
