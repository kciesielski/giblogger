package org.kpc.giblog

import akka.actor.{ActorRef, Props, ActorSystem}
import org.kpc.giblog.actors.{RegularJobLogWriter, DisasterLogWriter, NoiseLogWriter}
import scala.concurrent.duration._

object GibberishLogger extends App {

  val actorSystem = ActorSystem.create("gibberishLogger")

  import actorSystem.dispatcher

  val appRuntime = 1 hour

  val noiseWriter = actorSystem.actorOf(Props(new NoiseLogWriter()))
  val disasterWriter = actorSystem.actorOf(Props(new DisasterLogWriter()))
  val regularJobWriter = actorSystem.actorOf(Props(new RegularJobLogWriter(batchDuration = 1 minute, waitDuration = 5 minutes)))

  regularJobWriter ! "start"
  noiseWriter ! "loop"

  scheduleDisaster(disasterWriter, 35 minutes)

  Thread.sleep(appRuntime.toMillis)
  actorSystem.shutdown()


  private def scheduleDisaster(actor: ActorRef, when: FiniteDuration) {
    actorSystem.scheduler.scheduleOnce(when, actor, "boom")
  }

}

