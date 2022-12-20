package cz.cvut.fit.bioop.hackernewsclient.business

import scala.Console.WHITE

object ItemUtils {
  def unixTimeToDate(unixTime: Long): String = {
    val date = new java.util.Date(unixTime * 1000L)
    val sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"))
    sdf.format(date)
  }

  def replaceHtmlEntities(text: String): String = {
    text
      .replaceAll("&#x27;", "'")
      .replaceAll("&#x2F;", "/")
      .replaceAll("&quot;", "\"")
      .replaceAll("&amp;", "&")
      .replaceAll("&lt;", "<")
      .replaceAll("&gt;", ">")
      .replaceAll("<p>", "\n")
      .replaceAll("<i>", "\u001b[3m")
      .replaceAll("</i>", s"\u001b[0m$WHITE")
      .replaceAll("<em>", "\u001b[3m")
      .replaceAll("</em>", "\u001b[0m")
      .replaceAll("<b>", "\u001b[1m")
      .replaceAll("</b>", "\u001b[0m")
      .replaceAll("<strong>", "\u001b[1m")
      .replaceAll("</strong>", "\u001b[0m")
  }
}

