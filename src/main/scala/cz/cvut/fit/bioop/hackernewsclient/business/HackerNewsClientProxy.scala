package cz.cvut.fit.bioop.hackernewsclient.business

import cz.cvut.fit.bioop.hackernewsclient.models.{Story, User}


class HackerNewsClientProxy(api: HackerNewsClient) extends HackerNewsApi {
  var cacheTopStories: List[Int] = List.empty
  var cacheStory: Map[Int, Story] = Map.empty
  var cacheUsers: Map[String, User] = Map.empty

  override def topStories(): List[Int] = {
    if (cacheTopStories.isEmpty) {
      cacheTopStories = api.topStories()
    }
    cacheTopStories
  }

  override def story(id: Int): Story = {
    cacheStory.get(id) match {
      case Some(story) => story
      case None =>
        val story = api.story(id)
        cacheStory += (id -> story)
        story
    }
  }

  override def user(id: String): User = {
    cacheUsers.get(id) match {
      case Some(user) => user
      case None =>
        val user = api.user(id)
        cacheUsers += (id -> user)
        user
    }
  }
}
