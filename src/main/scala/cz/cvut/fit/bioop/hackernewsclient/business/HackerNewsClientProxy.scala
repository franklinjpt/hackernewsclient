package cz.cvut.fit.bioop.hackernewsclient.business

import cz.cvut.fit.bioop.hackernewsclient.models.{Story, User}


class HackerNewsClientProxy(api: HackerNewsClient) extends HackerNewsApi {
  var maxCacheAge: Long = 15000

  var cacheTopStories: List[Int] = List.empty
  var cacheBestStories: List[Int] = List.empty
  var cacheStory: Map[Int, (Story, Long)] = Map.empty // Store the timestamp when the story was added to the cache
  var cacheUsers: Map[String, (User, Long)] = Map.empty // Store the timestamp when the user was added to the cache

  override def topStories(): List[Int] = {
    if (cacheTopStories.isEmpty || System.currentTimeMillis() - cacheTopStories.head > maxCacheAge) {
      cacheTopStories = api.topStories()
    }
    cacheTopStories
  }

  override def bestStories(): List[Int] = {
    if (cacheBestStories.isEmpty || System.currentTimeMillis() - cacheBestStories.head > maxCacheAge) {
      cacheBestStories = api.bestStories()
    }
    cacheBestStories
  }

  override def story(id: Int): Story = {
    cacheStory.get(id) match {
      case Some((story, timestamp)) =>
        // If the story is in the cache, check if it has exceeded the maxCacheAge
      if (System.currentTimeMillis() - timestamp > maxCacheAge) {
        // If it has exceeded the maxCacheAge, fetch the story from the api and update the cache
        val updatedStory = api.story(id)
        cacheStory += (id -> (updatedStory, System.currentTimeMillis()))
        updatedStory
      } else {
        // If it has not exceeded the maxCacheAge, return the story from the cache
        story
      }
      case None =>
        // If the story is not in the cache, fetch it from the api and add it to the cache
        val story = api.story(id)
        cacheStory += (id -> (story, System.currentTimeMillis()))
        story
    }
  }

  override def user(id: String): User = {
    cacheUsers.get(id) match {
      case Some((user, timestamp)) =>
      // If the user is in the cache, check if it has exceeded the maxCacheAge
        if (System.currentTimeMillis() - timestamp > maxCacheAge) {
          // If it has exceeded the maxCacheAge, fetch the user from the api and update the cache
          val updatedUser = api.user(id)
          cacheUsers += (id -> (updatedUser, System.currentTimeMillis()))
          updatedUser
        } else {
          // If it has not exceeded the maxCacheAge, return the user from the cache
          user
        }
      case None =>
        // If the user is not in the cache, fetch it from the api and add it to the cache
        val user = api.user(id)
        cacheUsers += (id -> (user, System.currentTimeMillis()))
        user
    }
  }
}
