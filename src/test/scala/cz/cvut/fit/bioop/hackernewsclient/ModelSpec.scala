package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.models.{Comment, Story, User}
import org.scalatest.FlatSpec
import upickle.default._

class ModelSpec extends FlatSpec{

  "A Story model" should "parse a Json to an object" in {
    val json =
      """{
        |"by" : "dhouston",
        |  "descendants" : 71,
        |  "id" : 8863,
        |  "kids" : [ 9224, 8917, 8952, 8958, 8884, 8887, 8869, 8873, 8940, 8908, 9005, 9671, 9067, 9055, 8865, 8881, 8872, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8870, 8878, 8980, 8934, 8943, 8876 ],
        |  "score" : 104,
        |  "time" : 1175714200,
        |  "title" : "My YC app: Dropbox - Throw away your USB drive",
        |  "type" : "story",
        |  "url" : "http://www.getdropbox.com/u/2/screencast.html"
        |}""".stripMargin

    val result: Story = read[Story](json)

    assert(result.by == "dhouston")
    assert(result.descendants == 71)
    assert(result.id == 8863)
    assert(result.kids == List(9224, 8917, 8952, 8958, 8884, 8887, 8869, 8873, 8940, 8908, 9005, 9671, 9067, 9055, 8865, 8881, 8872, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8870, 8878, 8980, 8934, 8943, 8876))
    assert(result.score == 104)
    assert(result.time == 1175714200)
    assert(result.title == "My YC app: Dropbox - Throw away your USB drive")
    assert(result.`type` == "story")
    assert(result.url == "http://www.getdropbox.com/u/2/screencast.html")
  }

  "A User model" should "parse a Json to an object" in {
    val json =
      """{
        |"about" : "http://norvig.com",
        |  "created" : 1190398535,
        |  "id" : "norvig",
        |  "karma" : 1197,
        |  "submitted" : [ 28836275, 28836263 ]
        |}""".stripMargin

    val result: User = read[User](json)

    assert(result.about == "http://norvig.com")
    assert(result.created == 1190398535)
    assert(result.id == "norvig")
    assert(result.karma == 1197)
    assert(result.submitted == List(28836275, 28836263))
  }

  "A Comment model" should "parse a Json to an object" in {
    val json =
      """{
        |"by" : "dhouston",
        |  "id" : 2921983,
        |  "kids" : [ 2922097, 2922429, 2924562, 2922709, 2922573, 2922140, 2922141 ],
        |  "parent" : 2921506,
        |  "text" : "Aw, I really wanted to use this.",
        |  "time" : 1314211127,
        |  "type" : "comment"
        |}""".stripMargin

    val result: Comment = read[Comment](json)

    assert(result.by == "dhouston")
    assert(result.id == 2921983)
    assert(result.text == "Aw, I really wanted to use this.")
    assert(result.time == 1314211127)
  }

}