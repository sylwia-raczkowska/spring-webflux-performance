package com.raczkowska.performancelaunchblocking

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class BlockingLaunchGatlingSimulation extends Simulation {


  val httpConfig = http
    .baseUrl("http://localhost:8080")
    .contentTypeHeader("application/json")
    .acceptHeader("application/json")
    .shareConnections

  val basicLoad = scenario("LOAD_TEST")
    .exec(BasicLoad.addPersonTest)

  setUp(
    basicLoad.inject(
      rampUsers(20000) during (30)
    ).protocols(httpConfig)
  )

}


object BasicLoad {

  val start =
    exec(
      http("Get launch by id")
        .get("/launch-blocking")
        .queryParam("id", "5f350cf9a89af6551a2de4c9")
        .check(status is 200)
    )

  val addPersonTest =
    exec(http("add-launch-test")
      .post("/launch-blocking")
      .body(StringBody(
        s"""
           | {
           |  "name": "test-firstName",
           |  "description": "test-lastName"
           | }
         """.stripMargin
      )).check(status.is(201)))
}

