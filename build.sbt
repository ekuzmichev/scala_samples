name := "scala-samples"

ThisBuild / organization := "ru.ekuzmichev"
ThisBuild / version := "0.1-SNAPSHOT"
ThisBuild / scalaVersion := "2.12.8"
ThisBuild / autoCompilerPlugins := true

lazy val root =
  project
    .in(file("."))
    .aggregate(
      `cats-retry-samples`,
      `cats-effect`,
      lang,
      `zio-samples`,
      `zio-kafka`
    )

lazy val `cats-retry-samples` =
  project
    .settings(
      libraryDependencies ++= Seq(libs.catsRetry),
      Seq(scalacOptions ++= commonScalaOptions)
    )

lazy val `cats-effect` =
  project
    .settings(
      libraryDependencies ++= Seq(
        libs.catsEffect,
        libs.munitCatsEffectTest,
        libs.catsEffectScalatest,
        libs.catsEffectTestkit,
        "io.chrisdavenport" %% "log4cats-core"  % "2.1.1",
        "io.chrisdavenport" %% "log4cats-slf4j" % "2.1.1",
        "org.slf4j" % "slf4j-simple" % "1.7.32"
      ),
      Seq(scalacOptions ++= commonScalaOptions)
    )

lazy val lang =
  project
    .settings(
      libraryDependencies ++= Seq(
        "com.jakewharton.byteunits" % "byteunits" % "0.9.1"
      ),
      Seq(scalacOptions ++= commonScalaOptions)
    )

lazy val `zio-samples` =
  project
    .settings(
      libraryDependencies ++= Seq(libs.zio),
      Seq(scalacOptions ++= commonScalaOptions)
    )

lazy val `zio-kafka` =
  project
    .settings(
      libraryDependencies ++= Seq(
        libs.zioStreams,
        libs.zioKafka,
        libs.kafkaStreamsCirce
      ),
      Seq(scalacOptions ++= commonScalaOptions)
    )

lazy val commonScalaOptions =
  Seq(
    "-Xfatal-warnings",
    "-Ypartial-unification",
    "-feature",
    "-deprecation",
    "-unchecked",
    "-language:postfixOps",
    "-language:higherKinds"
  )

lazy val libs = new {
  val catsRetryV         = "1.1.0"
  val kafkaStreamsCirceV = "0.6.3"
  val zioV               = "1.0.2"
  val zioKafkaV          = "0.14.0"
  val catsEffectV        = "3.3.0"

  val catsRetry           = "com.github.cb372" %% "cats-retry"                    % catsRetryV
  val catsEffect          = "org.typelevel"    %% "cats-effect"                   % catsEffectV withSources () withJavadoc ()
  val munitCatsEffectTest = "org.typelevel"    %% "munit-cats-effect-3"           % "1.0.6" % Test
  val catsEffectScalatest = "org.typelevel"    %% "cats-effect-testing-scalatest" % "1.4.0" % Test
  val catsEffectTestkit   = "org.typelevel"    %% "cats-effect-testkit"           % catsEffectV % Test
  val kafkaStreamsCirce   = "com.goyeau"       %% "kafka-streams-circe"           % kafkaStreamsCirceV
  val zio                 = "dev.zio"          %% "zio"                           % zioV
  val zioKafka            = "dev.zio"          %% "zio-kafka"                     % zioKafkaV
  val zioStreams          = "dev.zio"          %% "zio-streams"                   % zioV
}
