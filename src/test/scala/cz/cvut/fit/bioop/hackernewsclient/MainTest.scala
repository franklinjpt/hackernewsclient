package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.Main.displayPage
import cz.cvut.fit.bioop.hackernewsclient.business.{HackerNewsClient, HackerNewsClientProxy}
import org.mockito.MockitoSugar.{reset}
import org.scalatest.Matchers
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatest.{BeforeAndAfter, FlatSpec}

class MainTest extends FlatSpec with Matchers with BeforeAndAfter{

  val api = mock[HackerNewsClient]
  val proxy = new HackerNewsClientProxy(api)

  before {
    reset(api)
  }

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

  "the method displayPage" should "return an empty list if no page is provided or it's written in the wrong format" in {
    val result1 = displayPage(Array())
    assert(result1 == List())

    val result2 = displayPage(Array("foo=1"))
    assert(result2 == List())
  }

  it should "return an empty list if the page is out of range" in {
    val result1 = displayPage(Array("page=0"))
    assert(result1 == List())

    val result2 = displayPage(Array("page=100"))
    assert(result2 == List())
  }

  it should "return a list of integers from the correct range for a valid page" in {
    val result = displayPage(Array("page=2"))
    val expected = (10 until 20).toList
    assert(result == expected :+ 15000)
  }

  it should "return a ttl of 15000 by default" in {
    val result = displayPage(Array("page=1"))
    val expected = (0 until 10).toList
    assert(result == expected :+ 15000)
  }

}
