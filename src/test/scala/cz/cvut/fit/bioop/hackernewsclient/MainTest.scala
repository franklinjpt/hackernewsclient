package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.proxies.StoriesProxy
import org.mockito.MockitoSugar.mock
import org.scalatest.flatspec.AnyFlatSpec


class MainTest extends AnyFlatSpec {
  def captureOutput(f: => Unit): String = {
    val out = new java.io.ByteArrayOutputStream()
    Console.withOut(out) {
      f
    }
    out.toString
  }

  "Main" should "print 'No arguments provided' when no arguments are provided" in {
    val args = Array[String]()
    val expected = "No arguments provided"
    val actual = captureOutput(Main.proccessArgs(args)).trim
    assert(actual == expected)
  }

  it should "print 'Unknown command' when unknown command is provided" in {
    val args = Array[String]("unknown")
    val expected = "Unknown command"
    val actual = captureOutput(Main.proccessArgs(args)).trim
    assert(actual == expected)
  }

  it should "print the first top stories when 'top' command is provided" in {
    val args = Array[String]("top")
    val topStories = mock[StoriesProxy]
    val expected = captureOutput(topStories.displayItem("topstories"))
    val actual = captureOutput(Main.proccessArgs(args))
    assert(actual.contains(expected))
  }

  it should "print the top stories from the page that is provided on 'top <page>'" in {
    val args = Array[String]("top", "3")
    val topStories = mock[StoriesProxy]
    val expected = captureOutput(topStories.displayItem("topstories", Array("3")))
    val actual = captureOutput(Main.proccessArgs(args))
    assert(actual.contains(expected))
  }

  it should "print 'this page does not exist' when page that does not exist is provided" in {
    val args = Array[String]("top", "page=100000")
    val expected = "this page does not exist"
    val actual = captureOutput(Main.proccessArgs(args)).trim
    assert(actual == expected)
  }

  it should "print 'No page provided or it's written in wrong format' when page is not provided" in {
    val args = Array[String]("top", "page=")
    val expected = "No page provided or it's written in wrong format"
    val actual = captureOutput(Main.proccessArgs(args)).trim
    assert(actual == expected)
  }
}
