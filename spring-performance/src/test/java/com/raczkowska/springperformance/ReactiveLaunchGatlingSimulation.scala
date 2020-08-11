package com.raczkowska.springperformance

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

class ReactiveLaunchGatlingSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  val scn = scenario("Get Reactive Launches")
    .repeat(50) {
      exec(
        http("random_request")
          .get("/launches-reactive")
          .check(status.is(200))
      )
    }

  setUp(scn.inject(atOnceUsers(500)).protocols(httpProtocol))

}
