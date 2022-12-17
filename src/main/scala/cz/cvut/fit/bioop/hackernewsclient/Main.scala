package cz.cvut.fit.bioop.hackernewsclient
import cz.cvut.fit.bioop.hackernewsclient.proxies.{StoriesProxy, UserInfoProxy}

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
      println(s"${BLUE}Welcome to Hacker News Client!!!!!$RESET")
      println(s"${GREEN}This console application allows you to browse Hacker News stories and user profiles.")
      println(s"${GREEN}This application fetch the first 50 stories from the API and then caches them.$RESET")
      println(s"${RED}Instructions: $RESET")
      println(s"${GREEN}To display stories you can type the number shown in the menu, e.g. 1 for top stories.")
      println(s"${BOLD}Pages are 10 stories long.$RESET")
      println(s"${GREEN}To display a different page you can type page=2, page=3, etc. for instance, to display page 3 from Best stories:")
      println("3 page=3")
      println(s"${BOLD}The last page is number 5.$RESET")
      println(s"${GREEN}To display user profile you can type \"user\" and the name of the user (e.g. user=pg).$RESET")
      println(s"${YELLOW}1 - Top stories")
      println("2 - New stories")
      println("3 - Best stories")
      println("4 - Ask stories")
      println("5 - Show stories")
      println("6 - Job stories")
      println("7 - Clear cache")
      println("User - User profile")
      println("q - Quit")
      print(s"Choose an option: $RESET")
      input = readLine()
      println("")
      input match {
        case "1" => topstories.displayItem("topstories")
        case s"1 ${commands}" => topstories.displayItem("topstories", commands.split(" "))
        case "2" => newstories.displayItem("newstories")
        case s"2 ${commands}" => newstories.displayItem("newstories", commands.split(" "))
        case "3" => beststories.displayItem("beststories")
        case s"3 ${commands}" => beststories.displayItem("beststories", commands.split(" "))
        case "4" => askstories.displayItem("askstories")
        case s"4 ${commands}" => askstories.displayItem("askstories", commands.split(" "))
        case "5" => showstories.displayItem("showstories")
        case s"5 ${commands}" => showstories.displayItem("showstories", commands.split(" "))
        case "6" => jobstories.displayItem("jobstories")
        case s"6 ${commands}" => jobstories.displayItem("jobstories", commands.split(" "))
        case "7" => topstories.storyList = List()
                    newstories.storyList = List()
                    beststories.storyList = List()
                    askstories.storyList = List()
                    showstories.storyList = List()
                    jobstories.storyList = List()
                    user.userInfo = List()
        case s"user=${name}" => user.displayItem(name)
        case "q" => println("Bye!")
        case _ => println("Invalid option")
      }
    }
  }
}
