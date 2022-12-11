package cz.cvut.fit.bioop.hackernewsclient
import upickle.default.{ReadWriter => RW, macroRW}

case class User(about: String = "*Without Description*", created: Long, id: String, karma: Int, submitted: List[Int])
object User {
  implicit def rw: RW[User] = macroRW
}

