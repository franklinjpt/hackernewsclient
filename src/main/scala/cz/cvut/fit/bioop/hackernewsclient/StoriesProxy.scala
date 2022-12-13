package cz.cvut.fit.bioop.hackernewsclient
import scala.Console._

class StoriesProxy {
  val stories = new Stories()
  var storyList: List[Story] = List()

  def displayItem(name: String, commands: Array[String] = Array()): Unit = {
        val commandValues = commands.flatMap(l => {
          val split = l.split("=")
          if (split.length == 2) {
            Some(split(0) -> split(1).toInt)
          } else {
            None
          }
        }).toMap

    if (storyList.isEmpty) {
      storyList = stories.getStories(name)
    }
    val page = commandValues.getOrElse("page", 1)
    val sliceFrom = (page - 1) * 10
    val sliceTo = page * 10
    val storiesToShow = storyList.slice(sliceFrom, sliceTo)
    storiesToShow.foreach(story => {
      println("----------------------------------------")
      println(s"$YELLOW${story.title} $RESET (${if(story.url != "") story.url else story.text})")
      println(s"$WHITE${story.score} points by ${story.by} | ${story.descendants} comments $RESET")
      println("")
    })
  }
}
