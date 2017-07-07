import sbt._
import sbt.Keys._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.universal.UniversalPlugin
import com.typesafe.sbt.packager.universal.UniversalPlugin.autoImport._
// *****************************************************************************
// Projects
// *****************************************************************************
Defaults.itSettings
lazy val `dependencyinjection` =
  project
    .in(file("."))
    .enablePlugins(UniversalPlugin)
    .enablePlugins(JavaAppPackaging)
    .settings(settings)
    .settings(
      mainClass in (Compile,run) := Some("com.test.di.constructor.App"),
      topLevelDirectory := None,
      name in Universal := name.value
    )
    .settings(
      libraryDependencies ++= Seq(
        library.pureconfig,
        library.akkaSlf4j,
        library.logback,
        library.log4Scala,
        library.refined,
        library.refinedconf,
        library.macwire,
        library.cats,
        library.catsCore,
        library.akkaTest  % "test, it",
        library.scalaTest % "test, it",
        library.scalaMock % "test, it"
      )
    ).configs(IntegrationTest)
    .settings(parallelExecution in IntegrationTest := false)
    .settings(testOptions += Tests.Argument("-oF"))

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val akkaHttp    = "10.0.6"
      val akka        = "2.4.17"
      val circe       = "0.7.1"
      val logback     = "1.2.2"
      val log4Scala   = "3.5.0"
      val scalaMock   = "3.6.0"
      val scalaTest   = "3.0.3"
      val pureconfig  = "0.7.2"
      val refined     = "0.8.2"
      val macwire     = "2.3.0"
      val cats        = "0.9.0"
    }

    val pureconfig  = "com.github.pureconfig"%% "pureconfig"                  % Version.pureconfig
    val akkaSlf4j   = "com.typesafe.akka"    %% "akka-slf4j"                  % Version.akka
    val logback     = "ch.qos.logback"       %  "logback-classic"             % Version.logback
    val log4Scala   = "com.typesafe.scala-logging" %% "scala-logging"         % Version.log4Scala
    val akkaTest    = "com.typesafe.akka"    %% "akka-http-testkit"           % Version.akkaHttp
    val scalaTest   = "org.scalatest"        %% "scalatest"                   % Version.scalaTest
    val scalaMock   = "org.scalamock"        %% "scalamock-scalatest-support" % Version.scalaMock
    val refined     = "eu.timepit"           %% "refined"                     % Version.refined
    val refinedconf = "eu.timepit"           %% "refined-pureconfig"          % Version.refined
    val macwire     =  "com.softwaremill.macwire" %% "macros"                 % Version.macwire
    val catsCore    =  "org.typelevel"       %% "cats-core"                   % Version.cats
    val cats        =  "org.typelevel"       %% "cats"                        % Version.cats

  }

// *****************************************************************************
// Settings
// *****************************************************************************        |

lazy val settings =
  commonSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.2",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-encoding", "utf8",
      "-feature",
      "-explaintypes",
      "-target:jvm-1.8",
      "-language:existentials",
      "-language:higherKinds",
      "-language:implicitConversions",
      "-Ydelambdafy:method",
      "-Xcheckinit",
      "-Xfuture",
      "-Xlint",
      "-Xlint:-nullary-unit",
      "-Ywarn-unused",
      "-Ywarn-unused-import",
      "-Ywarn-dead-code",
      "-Ywarn-value-discard"
    ),
    javacOptions ++= Seq(
      "-source", "1.8",
      "-target", "1.8"
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3"),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value)
    //wartremoverErrors ++= Warts.unsafe
  )