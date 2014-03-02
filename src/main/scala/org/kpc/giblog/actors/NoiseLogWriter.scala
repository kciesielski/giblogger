package org.kpc.giblog.actors

import akka.actor.Actor
import com.typesafe.scalalogging.slf4j.Logging
import scala.concurrent.duration._

class NoiseLogWriter extends Actor with Logging {
  import context.dispatcher

  override def receive = {
    case "loop" =>
      logger.info("noise")
      context.system.scheduler.scheduleOnce(20 seconds, self, "loop")
  }
}