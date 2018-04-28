package com.mobvista.leidu.parse

import org.joda.time.format.DateTimeFormat

object DateParse {
  def main(args: Array[String]): Unit = {
    val dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'GMT'")
    val time = dateTimeFormatter.parseDateTime("2017-09-27T02:16:02.303GMT").withSecondOfMinute(0).withMillisOfSecond(0).getMillis
    println(time)

  }
}
