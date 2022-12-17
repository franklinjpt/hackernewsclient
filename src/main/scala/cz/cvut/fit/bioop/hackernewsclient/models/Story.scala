package cz.cvut.fit.bioop.hackernewsclient.models

import upickle.default.{macroRW, ReadWriter => RW}

case class Story(by: String, descendants: Int = 0, id: Int, kids: List[Int] = List.empty,
                 score: Int = 0, time: Int, title: String, `type`: String, url: String = "", text: String = "")
object Story {
  implicit def rw: RW[Story] = macroRW
}
