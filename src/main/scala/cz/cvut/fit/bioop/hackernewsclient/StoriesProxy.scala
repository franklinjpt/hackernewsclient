package cz.cvut.fit.bioop.hackernewsclient
import scala.Console._

class StoriesProxy extends Item {
  val stories = new Stories()
  var storyList: List[Story] = List()
  override def displayItem(name: String): Unit = {
    if (storyList.isEmpty) {
      storyList = stories.getStories(name)
    }
    storyList.foreach(story => {
      println("----------------------------------------")
      println(s"$YELLOW${story.title} $RESET (${if(story.url != "") story.url else story.text})")
      println(s"$WHITE${story.score} points by ${story.by} | ${story.descendants} comments $RESET")
      println("")
    })
  }
}
