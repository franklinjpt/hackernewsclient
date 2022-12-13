package cz.cvut.fit.bioop.hackernewsclient

import scala.Console.{MAGENTA, RESET}

class Stories{
  val stories: List[Story] = List()

  def getStories(name: String): List[Story] = {

    println(s"${MAGENTA}First time fetching stories$RESET")
    val response = scala.io.Source.fromURL(s"https://hacker-news.firebaseio.com/v0/$name.json")
    val responseStr = response.mkString
    response.close()

    val ids = ujson.read(responseStr).arr.take(50).map(_.num.toInt).toList

    val stories = ids.map(id => {
      val response = scala.io.Source.fromURL(s"https://hacker-news.firebaseio.com/v0/item/$id.json")
      val str = response.mkString
      response.close()
      val item = ujson.read(str)
      upickle.default.read[Story](item)
    })
    stories
  }
}
