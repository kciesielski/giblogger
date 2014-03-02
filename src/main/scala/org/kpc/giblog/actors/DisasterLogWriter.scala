package org.kpc.giblog.actors

import akka.actor.Actor
import com.typesafe.scalalogging.slf4j.Logging

/**
 * Disaster will happen only once.
 */
class DisasterLogWriter extends Actor with Logging {

  override def receive = {
    case "boom" =>
      logger.error("DISASTER") // TODO
  }

}
