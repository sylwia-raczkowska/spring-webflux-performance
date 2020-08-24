package com.raczkowska.performancelaunchreactive

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

class ReactiveLaunchGatlingSimulation extends Simulation {


  val httpConfig = http
    .baseUrl("http://localhost:8080")
    .contentTypeHeader("application/json")
    .acceptHeader("application/json")
    .shareConnections

  val basicLoad = scenario("LOAD_TEST")
    .exec(BasicLoad.addLaunch)

  setUp(
    basicLoad.inject(
      rampUsers(5000) during (30)
    ).protocols(httpConfig)
  )

}


object BasicLoad {

  val addLaunch =
    exec(http("add-launch-test")
      .post("/launch-reactive")
      .body(StringBody(
        s"""
           | {
           |  "name": "test-name",
           |  "description": "test-description"
           | }
         """.stripMargin
      )).check(status.is(201)))
}

//5000
//10000
