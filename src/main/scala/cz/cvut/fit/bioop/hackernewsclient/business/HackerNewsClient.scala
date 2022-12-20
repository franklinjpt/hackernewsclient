package cz.cvut.fit.bioop.hackernewsclient.business

import cz.cvut.fit.bioop.hackernewsclient.models.{Story, User}
import upickle.default._

class HackerNewsClient extends HackerNewsApi {
  private val apiUrl = "https://hacker-news.firebaseio.com/v0/"

  def fetch[T: Reader](path: String): T = {
    val response = scala.io.Source.fromURL(s"$apiUrl/$path.json")
    val responseStr = response.mkString
    response.close()
    read[T](responseStr)
  }

  override def topStories(): List[Int] = fetch[List[Int]]("topstories").take(50)

  override def bestStories(): List[Int] = fetch[List[Int]]("beststories").take(50)
  override def story(id: Int): Story = fetch[Story](s"item/$id")

  override def user(id: String): User = fetch[User](s"user/$id")
}