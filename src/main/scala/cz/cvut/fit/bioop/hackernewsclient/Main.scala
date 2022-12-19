package cz.cvut.fit.bioop.hackernewsclient
import cz.cvut.fit.bioop.hackernewsclient.proxies.{StoriesProxy, UserInfoProxy}

import scala.Console.println


object Main {

  def proccessArgs(args: Array[String]): Unit = {


    if (args.length == 0) {
        println("No arguments provided")
        return
    }

    val fullArg = args.mkString(" ")

    fullArg match {
      case s"top ${commands}" =>
        val storiesProxy = new StoriesProxy()
        storiesProxy.displayItem("topstories", commands.split(" "))
      case "top" =>
        val storiesProxy = new StoriesProxy()
        storiesProxy.displayItem("topstories", Array("page=1"))
      case s"user=${commands}" =>
        val userInfoProxy = new UserInfoProxy()
        userInfoProxy.displayItem(commands)
      case _ => println("Unknown command")
    }
  }

  def main(args: Array[String]): Unit = {
    proccessArgs(args)
  }
}
