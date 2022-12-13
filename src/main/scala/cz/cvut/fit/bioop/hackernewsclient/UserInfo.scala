package cz.cvut.fit.bioop.hackernewsclient

import scala.Console.{MAGENTA, RESET}

class UserInfo extends Item {
  val user: User = User()
  override def displayItem(name: String): Unit = {
    println("User info")
  }

  def getUserInfo(name: String): User = {
    println(s"${MAGENTA}First time fetching user info $RESET")
    val response = scala.io.Source.fromURL(s"https://hacker-news.firebaseio.com/v0/user/$name.json")
    val responseStr = response.mkString
    response.close()
    val user = ujson.read(responseStr)
    upickle.default.read[User](user)
  }
}
