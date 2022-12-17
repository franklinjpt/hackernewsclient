package cz.cvut.fit.bioop.hackernewsclient.models

import upickle.default.{macroRW, ReadWriter => RW}

case class Comment (by: String = "", text: String = "", id: Int = 0, time: Int = 0)
object Comment {
  implicit def rw: RW[Comment] = macroRW
}
