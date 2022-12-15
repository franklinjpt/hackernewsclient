package cz.cvut.fit.bioop.hackernewsclient
import upickle.default.{ReadWriter => RW, macroRW}

case class Comment (by: String = "", text: String = "", id: Int = 0, time: Int = 0)
object Comment {
  implicit def rw: RW[Comment] = macroRW
}
