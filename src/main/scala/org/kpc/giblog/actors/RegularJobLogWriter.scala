package org.kpc.giblog.actors

import akka.actor.Actor
import com.typesafe.scalalogging.slf4j.Logging
import scala.concurrent.duration.FiniteDuration
import scala.util.Random

class RegularJobLogWriter(batchDuration: FiniteDuration, waitDuration: FiniteDuration) extends Actor with Logging {

  import context.dispatcher

  var jobId: Int = 0

  override def receive = {
    case "start" =>
      // TODO failure
      jobId = Random.nextInt(10000)
      logger.info(s"BatchStart! Starting a long batch job $jobId")
      context.system.scheduler.scheduleOnce(batchDuration, self, "end")
      
    case "end" =>
      logger.info(s"BatchEnd! Starting a long batch job $jobId")
      context.system.scheduler.scheduleOnce(waitDuration, self, "start")
  }

}
