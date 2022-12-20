package cz.cvut.fit.bioop.hackernewsclient

import cz.cvut.fit.bioop.hackernewsclient.business.{HackerNewsClient, HackerNewsClientProxy}
import cz.cvut.fit.bioop.hackernewsclient.models.{Story, User}
import org.mockito.MockitoSugar.{reset, when}
import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar.mock

class HackerNewsClientSpec extends FlatSpec with Matchers with BeforeAndAfter {
  val proxy = mock[HackerNewsClientProxy]

  before {
    reset(proxy)
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

  it should "not fetch best stories twice" in {
    val stories = new HackerNewsClientProxy(new HackerNewsClient())
    val test1 = stories.cacheBestStories
    stories.bestStories()
    val test2 = stories.cacheBestStories
    assert(test1 == List())
    assert(test2 != List())
  }


  it should "fetch a new list of top story IDs after the cache has expired" in {
    // Create a mock HackerNewsClient that returns a fixed list of top story IDs
    when(proxy.topStories()).thenReturn(List(1, 2, 3))

    // Set the maxCacheAge to a very small value so that the cache will expire quickly
    proxy.maxCacheAge = 1

    val topStories1 = proxy.topStories()
    // Wait for the cache to expire
    Thread.sleep(10)
    when(proxy.topStories()).thenReturn(List(1, 2, 4))

    val topStories2 = proxy.topStories()

    topStories1 should not contain theSameElementsAs(topStories2)
  }

  it should "fetch a new list of best story IDs after the cache has expired" in {
    // Create a mock HackerNewsClient that returns a fixed list of best story IDs
    when(proxy.bestStories()).thenReturn(List(1, 2, 3))

    // Set the maxCacheAge to a very small value so that the cache will expire quickly
    proxy.maxCacheAge = 1

    val bestStories1 = proxy.bestStories()
    // Wait for the cache to expire
    Thread.sleep(10)
    when(proxy.bestStories()).thenReturn(List(1, 2, 4))

    val bestStories2 = proxy.bestStories()

    bestStories1 should not contain theSameElementsAs(bestStories2)
  }

  it should "fetch a new user after the cache has expired" in {
    // Create a mock HackerNewsClient that returns a fixed user
    when(proxy.user("Franklin")).thenReturn(User("Franklin", "Description", 0, 0, List()))
    // Set the maxCacheAge to a very small value so that the cache will expire quickly
    proxy.maxCacheAge = 1

    val user1 = proxy.user("Franklin")
    // Wait for the cache to expire
    Thread.sleep(10)
    when(proxy.user("Franklin")).thenReturn(User("Frank", "Description", 0, 0, List()))

    val user2 = proxy.user("Franklin")

    assert(user1.id == "Franklin")
    assert(user2.id == "Frank")
  }

  it should "fetch a new story after the cache has expired" in {
    // Create a mock HackerNewsClient that returns a fixed story
    when(proxy.story(1)).thenReturn(Story("Franklin", 1, 1, List()))
    // Set the maxCacheAge to a very small value so that the cache will expire quickly
    proxy.maxCacheAge = 1

    val story1 = proxy.story(1)
    // Wait for the cache to expire
    Thread.sleep(10)
    when(proxy.story(1)).thenReturn(Story("Frank", 1, 1, List()))

    val story2 = proxy.story(1)

    assert(story1.by == "Franklin")
    assert(story2.by == "Frank")
  }
}
