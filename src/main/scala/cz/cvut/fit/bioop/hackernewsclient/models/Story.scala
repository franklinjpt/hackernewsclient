package cz.cvut.fit.bioop.hackernewsclient.models

import cz.cvut.fit.bioop.hackernewsclient.business.ItemUtils.{replaceHtmlEntities, unixTimeToDate}
import upickle.default.{macroRW, ReadWriter => RW}

import scala.Console._


case class Story(by: String = "", descendants: Int = 0, id: Int = 0, kids: List[Int] = List.empty,
                 score: Int = 0, time: Int = 0, title: String = "",  `type`: String = "", url: String = "", text: String = "") {
  def display() = {
    println("")
    println("----------------------------------------")
    println(s"$YELLOW${title} $RESET (${if (url != "") url else replaceHtmlEntities(text)})")
    println(s"$WHITE${score} points by ${by} | ${descendants} comments $RESET")
    if(kids != List.empty) displayComments(kids.take(1))
  }

  def displayComments(idComment: List[Int]): Unit = {
    println("---------------COMMENTS---------------")
    idComment.map(id => {
      val response = scala.io.Source.fromURL(s"https://hacker-news.firebaseio.com/v0/item/$id.json")
      val str = response.mkString
      response.close()
      val item = ujson.read(str)
      upickle.default.read[Comment](item)
    }).foreach(comment => {
      println(s"$GREEN${comment.by}$RESET ${unixTimeToDate(comment.time)}")
      println(s"$WHITE${replaceHtmlEntities(comment.text)} $RESET")
      println("")
    })
  }
}

object Story {
  implicit def rw: RW[Story] = macroRW
}
