lazy val info = new {
  val organization = "com.github.niqdev"
  val name         = "scala-fp"
  val scalaVersion = "2.13.5"
}

lazy val versions = new {
  // ecosystem
  val caliban          = "0.9.5"
  val catsCore         = "2.4.2"
  val catsEffect       = "2.3.3"
  val catsRetry        = "2.1.0"
  val circe            = "0.13.0"
  val doobie           = "0.12.1"
  val droste           = "0.8.0"
  val enumeratum       = "1.6.1"
  val enumeratumDoobie = "1.6.0"
  val flyway           = "7.7.0"
  val fs2              = "2.5.3"
  val http4s           = "0.21.20"
  val log4cats         = "1.1.1"
  val logback          = "1.2.3"
  val magnolia         = "0.17.0"
  val newtype          = "0.4.4"
  val refined          = "0.9.21"
  val shapeless        = "2.3.3"
  val squants          = "1.7.4"
  val zio              = "1.0.5"
  val zioInteropCats   = "2.3.1.0"

  // test
  val scalacheck    = "1.15.3"
  val scalatest     = "3.2.6"
  val scalatestplus = "3.2.6.0"

  // common
  val kindProjector = "0.11.3"
}

lazy val dependencies = new {
  lazy val ecosystem = Seq(
    "com.github.ghostdogpr" %% "caliban"                   % versions.caliban,
    "com.github.ghostdogpr" %% "caliban-cats"              % versions.caliban,
    "com.github.ghostdogpr" %% "caliban-http4s"            % versions.caliban,
    "org.typelevel"         %% "cats-core"                 % versions.catsCore,
    "org.typelevel"         %% "cats-effect"               % versions.catsEffect,
    "com.github.cb372"      %% "cats-retry"                % versions.catsRetry,
    "io.circe"              %% "circe-core"                % versions.circe,
    "io.circe"              %% "circe-generic"             % versions.circe,
    "io.circe"              %% "circe-parser"              % versions.circe,
    "io.circe"              %% "circe-refined"             % versions.circe,
    "org.tpolecat"          %% "doobie-core"               % versions.doobie,
    "org.tpolecat"          %% "doobie-h2"                 % versions.doobie,
    "org.tpolecat"          %% "doobie-refined"            % versions.doobie,
    "io.higherkindness"     %% "droste-core"               % versions.droste,
    "com.beachape"          %% "enumeratum"                % versions.enumeratum,
    "com.beachape"          %% "enumeratum-circe"          % versions.enumeratum,
    "com.beachape"          %% "enumeratum-doobie"         % versions.enumeratumDoobie,
    "org.flywaydb"           % "flyway-core"               % versions.flyway,
    "co.fs2"                %% "fs2-core"                  % versions.fs2,
    "co.fs2"                %% "fs2-io"                    % versions.fs2,
    "org.http4s"            %% "http4s-blaze-client"       % versions.http4s,
    "org.http4s"            %% "http4s-blaze-server"       % versions.http4s,
    "org.http4s"            %% "http4s-dsl"                % versions.http4s,
    "org.http4s"            %% "http4s-prometheus-metrics" % versions.http4s,
    "io.chrisdavenport"     %% "log4cats-core"             % versions.log4cats,
    "io.chrisdavenport"     %% "log4cats-slf4j"            % versions.log4cats,
    "ch.qos.logback"         % "logback-classic"           % versions.logback % Runtime,
    "com.propensive"        %% "magnolia"                  % versions.magnolia,
    "io.estatico"           %% "newtype"                   % versions.newtype,
    "eu.timepit"            %% "refined"                   % versions.refined,
    "com.chuusai"           %% "shapeless"                 % versions.shapeless,
    "org.typelevel"         %% "squants"                   % versions.squants,
    "dev.zio"               %% "zio"                       % versions.zio,
    "dev.zio"               %% "zio-interop-cats"          % versions.zioInteropCats,
    "dev.zio"               %% "zio-streams"               % versions.zio
  )

  lazy val test = Seq(
    "org.tpolecat"      %% "doobie-scalatest"      % versions.doobie        % Test,
    "com.beachape"      %% "enumeratum-scalacheck" % versions.enumeratum    % Test,
    "org.scalacheck"    %% "scalacheck"            % versions.scalacheck    % Test,
    "org.scalatestplus" %% "scalacheck-1-15"       % versions.scalatestplus % Test,
    "org.scalatest"     %% "scalatest"             % versions.scalatest     % Test
  )
}

lazy val commonSettings = Seq(
  organization := info.organization,
  scalaVersion := info.scalaVersion,
  //javaOptions ++= Seq("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"),
  javacOptions ++= Seq("-source", "11"),
  // https://docs.scala-lang.org/overviews/compiler-options/index.html
  // https://tpolecat.github.io/2017/04/25/scalac-flags.html
  // https://github.com/DavidGregory084/sbt-tpolecat
  // https://nathankleyn.com/2019/05/13/recommended-scalac-flags-for-2-13
  // http://eed3si9n.com/stricter-scala-with-xlint-xfatal-warnings-and-scalafix
  // https://alexn.org/blog/2020/05/26/scala-fatal-warnings.html
  scalacOptions ++= Seq(
    "-encoding",
    "UTF-8",                         // source files are in UTF-8
    "-unchecked",                    // Enable additional warnings where generated code depends on assumptions.
    "-explaintypes",                 // explain type errors in more detail
    "-language:existentials",        // existential types (besides wildcard types) can be written and inferred
    "-language:higherKinds",         // allow higher kinded types without `import scala.language.higherKinds`
    "-language:implicitConversions", // allow definition of implicit functions called views
    "-Xlint",                        // enable handy linter warnings
    "-Wconf:any:error",              // configurable warnings see https://github.com/scala/scala/pull/8373
    "-Ymacro-annotations",           // required by newtype
    "-Xsource:2.13"
  ),
  addCompilerPlugin("org.typelevel" %% "kind-projector" % versions.kindProjector cross CrossVersion.full)
)

lazy val common = (project in file("modules/common"))
  .settings(commonSettings)
  .settings(
    name := s"${info.name}-common",
    libraryDependencies ++= dependencies
      .test
      .map(_.withSources)
      .map(_.withJavadoc)
  )

lazy val fp = (project in file("modules/fp"))
  .dependsOn(common)
  .settings(commonSettings)
  .settings(
    name := s"${info.name}-fp",
    libraryDependencies ++= dependencies
      .test
      .map(_.withSources)
      .map(_.withJavadoc)
  )

lazy val ecosystem = (project in file("modules/ecosystem"))
  .settings(commonSettings)
  .settings(
    name := s"${info.name}-ecosystem",
    libraryDependencies ++= (dependencies.ecosystem ++ dependencies.test)
      .map(_.withSources)
      .map(_.withJavadoc)
  )

lazy val docs = (project in file("modules/docs"))
  .settings(commonSettings)
  .enablePlugins(MdocPlugin, DocusaurusPlugin)
  .settings(
    name := s"${info.name}-docs",
    mdocVariables := Map(
      "VERSION" -> version.value
    )
  )

lazy val root = project
  .in(file("."))
  .aggregate(common, fp, ecosystem, docs)
  .settings(
    name := info.name,
    addCommandAlias("checkFormat", ";scalafmtCheckAll;scalafmtSbtCheck"),
    addCommandAlias("format", ";scalafmtAll;scalafmtSbt"),
    addCommandAlias("update", ";dependencyUpdates;reload plugins;dependencyUpdates;reload return"),
    addCommandAlias("build", ";checkFormat;clean;test"),
    addCommandAlias("publish", ";mdoc;docusaurusCreateSite;docusaurusPublishGhpages")
  )
