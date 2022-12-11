package cz.cvut.fit.bioop.hackernewsclient
import scala.io.StdIn.readLine

object Main {
  def main(args: Array[String]): Unit = {
    val topstories = new StoriesProxy()
    val newstories = new StoriesProxy()
    val beststories = new StoriesProxy()
    val askstories = new StoriesProxy()
    val showstories = new StoriesProxy()
    var input = ""
    while (input != "q") {
      println("Welcome to Hacker News Client")
      println("Please enter the number of the list you want to see (top, new, best, ask, show, job)")
      println("1 - Top stories")
      println("2 - New stories")
      println("3 - Best stories")
      println("4 - Ask stories")
      println("5 - Show stories")
      println("q - Quit")
      println("Choose an option: ")
      input = readLine()
      input match {
        case "1" => topstories.displayItem("topstories")
        case "2" => newstories.displayItem("newstories")
        case "3" => beststories.displayItem("beststories")
        case "4" => askstories.displayItem("askstories")
        case "5" => showstories.displayItem("showstories")
        case "q" => println("Bye!")
        case _ => println("Invalid option")
      }
    }
  }
}
