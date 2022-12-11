package cz.cvut.fit.bioop.hackernewsclient
import upickle.default.{ReadWriter => RW, macroRW}

case class Story(by: String, descendants: Int = 0, id: Int, kids: List[Int] = List.empty,
                 score: Int = 0, time: Int, title: String, `type`: String, url: String = "")
object Story {
  implicit def rw: RW[Story] = macroRW
}
