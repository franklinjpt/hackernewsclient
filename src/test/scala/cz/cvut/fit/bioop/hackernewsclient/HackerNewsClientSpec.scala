package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.business.{HackerNewsClient, HackerNewsClientProxy}
import cz.cvut.fit.bioop.hackernewsclient.models.{Story, User}
import org.mockito.MockitoSugar.{reset, when}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar.mock

class HackerNewsClientSpec extends FlatSpec with Matchers with BeforeAndAfter {
  val api = mock[HackerNewsClient]
  val proxy = mock[HackerNewsClientProxy]

  before {
    reset(api)
  }

  "HackerNewsClient"  should "fetch a story" in {
    when(proxy.story(1)).thenReturn(Story("Franklin", 1, 1, List()))
    val story = proxy.story(1)
    assert(story.by == "Franklin")
  }

  it should "fetch a story with comments" in {
    when(proxy.story(1)).thenReturn(Story("Franklin", 1, 1, List(1, 2, 3)))
    val story = proxy.story(1)
    assert(story.by == "Franklin")
    assert(story.kids == List(1, 2, 3))
  }

  it should "fetch a user" in {
    when(proxy.user("Franklin")).thenReturn(User("Franklin", "Description", 0, 0, List()))
    val user = proxy.user("Franklin")
    assert(user.id == "Franklin")
    assert(user.about == "Description")
  }

  it should "not fetch a user when it doesn't exist" in {
    when(proxy.user("Franklin")).thenReturn(null)
    val user = proxy.user("Franklin")
    assert(user == null)
  }

  it should "not fetch a user twice" in {
    val user = new HackerNewsClientProxy(new HackerNewsClient)
    val test1 = user.cacheUsers
    user.user("Franklin")
    val test2 = user.cacheUsers
    assert(test1 == Map.empty)
    assert(test2 != Map.empty)
  }

  it should "not fetch top stories twice" in {
    val stories = new HackerNewsClientProxy(new HackerNewsClient())
    val test1 = stories.cacheTopStories
    stories.topStories()
    val test2 = stories.cacheTopStories
    assert(test1 == List())
    assert(test2 != List())
  }

}
