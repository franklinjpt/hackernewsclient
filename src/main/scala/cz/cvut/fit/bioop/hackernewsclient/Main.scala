package cz.cvut.fit.bioop.hackernewsclient
import scala.Console._
import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
    val topstories = new StoriesProxy()
    val newstories = new StoriesProxy()
    val beststories = new StoriesProxy()
    val askstories = new StoriesProxy()
    val showstories = new StoriesProxy()
    val jobstories = new StoriesProxy()
    val user = new UserInfoProxy()

    var input = ""
    while (input != "q") {
      println("----------------------------------------")
      println(s"${BLUE}Welcome to Hacker News Client!!!!!")
      println(s"${GREEN}Please enter the number of the list you want to see (top, new, best, ask, show, job, user) or quit (q)")
      println(s"If you want to see User info, please enter number and then the username with two dashes (e.g. 7 --username) $RESET")
      println(s"${YELLOW}1 - Top stories")
      println("2 - New stories")
      println("3 - Best stories")
      println("4 - Ask stories")
      println("5 - Show stories")
      println("6 - Job stories")
      println("7 - User info")
      println("q - Quit")
      print(s"Choose an option: $RESET")
      input = readLine()
      println("")
      input match {
        case "1" => topstories.displayItem("topstories")
        case "2" => newstories.displayItem("newstories")
        case "3" => beststories.displayItem("beststories")
        case "4" => askstories.displayItem("askstories")
        case "5" => showstories.displayItem("showstories")
        case "6" => jobstories.displayItem("jobstories")
        case s"7 --${name}" => user.displayItem(name)
        case "q" => println("Bye!")
        case _ => println("Invalid option")
      }
    }
  }
}
