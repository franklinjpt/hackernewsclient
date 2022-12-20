package cz.cvut.fit.bioop.hackernewsclient.business

import cz.cvut.fit.bioop.hackernewsclient.models.{Story, User}

trait HackerNewsApi {
  def topStories(): List[Int]
  def bestStories(): List[Int]
  def story(id: Int): Story
  def user(id: String): User
}
