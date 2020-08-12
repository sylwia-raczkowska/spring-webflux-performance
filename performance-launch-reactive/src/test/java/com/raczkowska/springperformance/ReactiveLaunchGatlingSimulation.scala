package com.raczkowska.springperformance

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class ReactiveLaunchGatlingSimulation extends Simulation {

  val scn = scenario("Get Reactive Launches")
    .repeat(5) {
      exec(
        http("random_request")
          .get("/launches-reactive")
          .queryParam("page", 0)
          .queryParam("size", 100)
          .check(status.is(200))
      )
    }

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")

  setUp(scn.inject(atOnceUsers(1000))).protocols(httpProtocol)
    .assertions(global.successfulRequests.percent.is(100))

}
