package cz.cvut.fit.bioop.hackernewsclient.models

import cz.cvut.fit.bioop.hackernewsclient.business.ItemUtils.{replaceHtmlEntities, unixTimeToDate}
import upickle.default.{macroRW, ReadWriter => RW}

import scala.Console._

case class User(id: String = "",about: String = "*Empty Description*", created: Long = 0, karma: Int = 0, submitted: List[Int] = List()) {
  def display(): Unit = {
    println("----------------------------------------")
    println(s"$YELLOW${id} $RESET")
    println(s"Description: $WHITE${replaceHtmlEntities(about)}$RESET")
    println(s"Created at: $WHITE${unixTimeToDate(created)}$RESET")
    println(s"Karma: $WHITE${karma}$RESET")
    println(s"Submitted items: $WHITE${submitted.length}$RESET")
    println("")
  }
}

object User {
  implicit def rw: RW[User] = macroRW
}

