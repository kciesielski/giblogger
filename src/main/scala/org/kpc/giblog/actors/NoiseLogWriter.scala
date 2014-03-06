package org.kpc.giblog.actors

import akka.actor.Actor
import com.typesafe.scalalogging.slf4j.Logging
import scala.concurrent.duration._
import scala.util.Random
import org.slf4j.Logger

class NoiseLogWriter extends Actor with Logging {

  import context.dispatcher
  // a silly way to put weights on different log levels...
  val functions: List[(String => Unit)] = List(info, info, info, info, info, warn, debug, debug, debug)

  def info(text: String) {
    logger.info(text)
  }

  def warn(text: String) {
    logger.warn(text)
  }

  def debug(text: String) {
    logger.debug(text)
  }

  def logRandomMsg() {
    NoiseLogWriter.processRandomLine(Random.shuffle(functions).head)
  }

  override def receive = {
    case "loop" =>
      logRandomMsg()
      val delay = Random.nextInt(1500)
      context.system.scheduler.scheduleOnce((500 + delay) millis, self, "loop")
  }
}

object NoiseLogWriter {

  def processRandomLine(fun: String => Unit) = {
    val lineNo = Random.nextInt(lines.length)
    fun(lines(lineNo))
  }

  val lines = List(
    "end connection 10.73.137.47:46846 (5 connections now open)",
    "cropping 530a24a4e4b0cad761093fbd: 18, 5, 684, 191",
    "/images/5318ee3fe4b05fe501f53820",
    "loadMostRecentPhotosRecursively: Ending recursion with image collection size = 500",
    "ImagesServlet 6 ms",
    "parsedBody JObject(List((mote,JArray(List())), (comment,JString()), (x,JDouble(0.04964018422567651)), (y,JDouble(0.05000000000000008)), (x2,JDouble(0.9996401842256765)), (y2,JInt(1))))",
    "EmptyUserName: 100006535621483",
    "updateMostRecentCache - after filter: 842 photos, set updateMostRecentTime",
    "/fql 1726 ms",
    "end connection 23.23.77.78:56548 (5 connections now open)",
    " HttpClientPoolStats = [leased: 1; pending: 0; available: 7; max: 50]",
    "Starting background batch services: OK",
    "Loading data from cache: 458483 bytes OK",
    "/me/profile 3 ms",
    "Calling query me/profile with token = jnvjetvuynhcgner7h439537538vbrdhywgr2",
    "Loading additional user data from storage",
    "Cannot find request id. Generating new identifier.",
    "User 49583 not activated. Activating.",
    "token 5318ee3fe4b05fe501f53820 expired, terminating session",
    "Clearing temp data... 344 objects to remove, 21 objects valid",
    "parsedBody JObject(Nil)",
    "loadMostRecentPhotosRecursively: Ending recursion with image collection size = 23",
    "SettingsServlet 7 ms",
    "Importing data from CSV file: import-454234.csv",
    "Finished importing import-454234.csv",
    "Requested data eport to XLS",
    "Generated temp-0da3423c.xls, sending to stream",
    "Stream closed, bytes sent successfully",
    "Parsing JSON object",
    "Fetching warehouse records with criteria: offset = 200, limit = 30",
    "No more elements, closing cursor (115ms)",
    "Cursor closed (22ms)",
    "Sending archive file archive77633.tar.gz to S3 (bucket app-archives/2014)",
    "Successfully purged SMS duplicates for user 7780a211",
    "Total elements found in file: 34874",
    "Network prefixes successfully imported",
    "Retrying http request after error response",
    "Access token obtained in 12ms",
    "queryItemPhotos 92ms"
  )
}