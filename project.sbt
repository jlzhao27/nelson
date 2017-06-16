//: ----------------------------------------------------------------------------
//: Copyright (C) 2017 Verizon.  All Rights Reserved.
//:
//:   Licensed under the Apache License, Version 2.0 (the "License");
//:   you may not use this file except in compliance with the License.
//:   You may obtain a copy of the License at
//:
//:       http://www.apache.org/licenses/LICENSE-2.0
//:
//:   Unless required by applicable law or agreed to in writing, software
//:   distributed under the License is distributed on an "AS IS" BASIS,
//:   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//:   See the License for the specific language governing permissions and
//:   limitations under the License.
//:
//: ----------------------------------------------------------------------------

organization in Global := "io.verizon.nelson"

scalaVersion in Global := "2.11.8"

lazy val nelson = project.in(file(".")).aggregate(docs, core, http)

lazy val core = project

lazy val http = project.dependsOn(core % "test->test;compile->compile")

lazy val docs = project

enablePlugins(DisablePublishingPlugin)

// by adding this to the root project, we make ensime happy
addCompilerPlugin(dependencies.si2712fix.plugin)

addCommandAlias("ci", ";test;coverageReport;coverageAggregate;tut;unidoc")

// TIM: This is a hack to get our docker image published for the http module
import sbtrelease._

releaseProcess += ReleaseStep(releaseStepTask(publish in Docker))