package org.kpc.giblog.actors

import akka.actor.Actor
import com.typesafe.scalalogging.slf4j.Logging
import scala.util.Random
import scala.concurrent.duration._

/**
 * Disaster will happen only once.
 */
class DisasterLogWriter extends Actor with Logging {

  import context.dispatcher

  override def receive = {
    case "boom" =>
      val errorCount = 90 + Random.nextInt(50)
      self ! LogErrorCommand(errorCount)
    case LogErrorCommand(count) =>
      val msg = Random.shuffle(DisasterLogWriter.messages).head
      logger.error(msg)
      if (count > 0) {
        val delay = Random.nextInt(1000)
        context.system.scheduler.scheduleOnce((100 + delay) millis, self, LogErrorCommand(count - 1))
      }

  }

}

case class LogErrorCommand(errorsLeft: Int)

object DisasterLogWriter {

  val messages = List(
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "org.some.db.driver.TimeoutException: cannot reach database, maximum opened connection count reached!\n\tat org.some.db.driver.pool.Pool.getFreeConnection(Pool.java:211)\n\tat org.some.db.driver.Connector.connect(Connector.java:305)\n\tat org.kpc.app.UserDao.authUser(UserDao:33)",
    "ERROR: User 45732 cannot log in, retrying",
    "ERROR: User 34341 cannot log in, retrying",
    "ERROR: User 44221 cannot log in, retrying",
    "ERROR: User 74341 cannot log in, retrying",
    "ERROR: User 34757 cannot log in, retrying",
    "ERROR: User 77662 cannot log in, retrying",
    "ERROR: User 11111 cannot log in, retrying",
    "ERROR: User 23532 cannot log in, retrying",
    "ERROR: User 76812 cannot log in, retrying",
    "ERROR: User 325631 cannot log in, retrying",
    "ERROR: User 170 cannot log in, retrying",
    "ERROR: User 65732 cannot log in, retrying"
  )
}