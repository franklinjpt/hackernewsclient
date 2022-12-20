package cz.cvut.fit.bioop.hackernewsclient
import cz.cvut.fit.bioop.hackernewsclient.business.{HackerNewsClient, HackerNewsClientProxy}

import scala.Console._


object Main {

  def main(args: Array[String]): Unit = {
    proccessArgs(args)
  }

  def displayPage(commands: Array[String]): List[Int] = {
    val commandValues = commands.flatMap(l => {
      val split = l.split("=")
      if (split.length == 2 && split(0) == "page") {
        Some(split(0) -> split(1).toInt)
      } else {
        None
      }
    }).toMap

    if (commandValues.isEmpty) {
      println(s"No page provided or it's written in wrong format")
      return List()
    }

    val page = commandValues.getOrElse("page", 1)
    if (page < 1 || page > 5) {
      println("this page does not exist")
      return List()
    }

    println("Page: " + page)
    val sliceFrom = (page - 1) * 10
    val sliceTo = page * 10
    (sliceFrom until sliceTo).toList
  }

  def displayHelp(): Unit = {
    println(s"${BLUE}Welcome to Hacker News Client!!!!!$RESET")
    println(s"${GREEN}This CLI allows you to browse Hacker News stories and user profiles.")
    println(s"${GREEN}This application fetch the first 50 stories from the API and then caches them.$RESET")
    println(s"${RED}Instructions: $RESET")
    println(s"${GREEN}To display top stories you can write in the CLI 'top' (by default it will display page 1)")
    println(s"${BOLD}Pages are 10 stories long.$RESET")
    println(s"${GREEN}To display a different page you can type page=2, page=3, etc. for instance, to display page 3 from Best stories:")
    println("best page=3")
    println(s"${BOLD}The last page is number 5.$RESET")
    println(s"${GREEN}To display user profile you can type \"user\" and the name of the user (e.g. user=pg).$RESET")
  }

  def proccessArgs(args: Array[String]): Unit = {
    val api = new HackerNewsClientProxy(new HackerNewsClient)

    if (args.length == 0) {
        println("No arguments provided")
        return
    }

    val fullArg = args.mkString(" ")

    fullArg match {
      case s"top ${commands}" =>
        val fromTo = displayPage(commands.split(" "))
        if (fromTo.isEmpty) return
        api.topStories().slice(fromTo.head, fromTo.last + 1).foreach(id => {
          val story = api.story(id)
          story.display()
        })
      case "top" =>
        println("page=1")
        api.topStories().take(11).foreach(id => {
          val story = api.story(id)
          story.display()
        })
      case s"user=${commands}" =>
        if(commands == "") {
          println("No username provided")
          return
        } else if (api.user(commands) == null) {
          println("User not found")
          return
        }
        api.user(commands).display()
      case "help" => displayHelp()
      case _ => println("Unknown command")
    }
  }

}
