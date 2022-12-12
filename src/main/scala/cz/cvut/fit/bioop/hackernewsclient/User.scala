package cz.cvut.fit.bioop.hackernewsclient
import upickle.default.{ReadWriter => RW, macroRW}

case class User(about: String = "*Empty Description*", created: Long = 0, id: String = "", karma: Int = 0, submitted: List[Int] = List())
object User {
  implicit def rw: RW[User] = macroRW
}

