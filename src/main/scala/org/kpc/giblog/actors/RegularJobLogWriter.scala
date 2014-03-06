package org.kpc.giblog.actors

import akka.actor.Actor
import com.typesafe.scalalogging.slf4j.Logging
import scala.concurrent.duration.FiniteDuration
import scala.util.Random

class RegularJobLogWriter(batchDuration: FiniteDuration, waitDuration: FiniteDuration) extends Actor with Logging {

  import context.dispatcher

  var jobId: Int = 0
  val failureIndex = 7
  var currentJobIndex = 0

  override def receive = {
    case "start" =>
      jobId = Random.nextInt(10000)
      currentJobIndex = currentJobIndex + 1
      if (currentJobIndex == failureIndex) {
        // skip one round
        context.system.scheduler.scheduleOnce(waitDuration + batchDuration, self, "start")
      }
      else {
        logger.info(s"BatchStart! Starting a long batch job $jobId")
        context.system.scheduler.scheduleOnce(batchDuration, self, "end")
      }
      
    case "end" =>
      logger.info(s"BatchEnd! Finishing batch job $jobId")
      context.system.scheduler.scheduleOnce(waitDuration, self, "start")
  }

}
