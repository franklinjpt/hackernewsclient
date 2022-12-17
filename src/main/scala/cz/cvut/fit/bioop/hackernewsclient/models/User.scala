package cz.cvut.fit.bioop.hackernewsclient.models

import upickle.default.{macroRW, ReadWriter => RW}

case class User(about: String = "*Empty Description*", created: Long = 0, id: String = "", karma: Int = 0, submitted: List[Int] = List())
object User {
  implicit def rw: RW[User] = macroRW
}

