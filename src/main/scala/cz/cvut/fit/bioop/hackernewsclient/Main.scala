package cz.cvut.fit.bioop.hackernewsclient

object Main {
  def main(args: Array[String]): Unit = {
    val commandValues = args.flatMap( l => {
        val split = l.split("=")
        if (split.length == 2) {
            Some(split(0) -> split(1).toInt)
        } else {
            None
        }
        }).toMap

    val request = args(0) match {
      case "top-stories"
      => requestStory(scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/topstories.json").mkString, commandValues)
      case "new-stories"
      => requestStory(scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/newstories.json").mkString, commandValues)
      case "best-stories"
      => requestStory(scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/beststories.json").mkString, commandValues)
      case "ask-stories"
      => requestStory(scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/askstories.json").mkString, commandValues)
      case "show-stories"
      => requestStory(scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/showstories.json").mkString, commandValues)
      case "job-stories"
      => requestStory(scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/jobstories.json").mkString, commandValues)
      case "user"
      => requestUser(scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/user/" + args(1) + ".json").mkString)
      case _ => "Invalid option"
    }
  }

  def requestStory(url: String, commands: Map[String,Int]): Unit = {

//    val page =  command.split("=")(1).toInt
    val page = commands.getOrElse("--page", 1)

//    val pageSize = if (others == "") 10 else others.split("=")(1).toInt
    val pageSize = commands.getOrElse("--page-size", 10)

    val sliceFrom = (page - 1) * pageSize
    val sliceTo = page * pageSize

    val ids = ujson.read(url).arr.slice(sliceFrom, sliceTo).take(pageSize).map(_.num.toInt)

    val stories = ids.map(id => {
      val requestItem = scala.io.Source.fromURL("https://hacker-news.firebaseio.com/v0/item/" + id + ".json")
      val item = ujson.read(requestItem.mkString)
      upickle.default.read[Story](item)
    })
    stories foreach println
  }

  def requestUser(url: String): Unit = {
    val userJson = ujson.read(url)
    val user = upickle.default.read[User](userJson)
    println(user)
  }
}
