package cz.cvut.fit.bioop.hackernewsclient
import scala.Console._
import ItemUtils._

class UserInfoProxy {
    val user = new UserInfo()
    var userInfo: List[User] = List()

    def displayItem(name: String): Unit = {
      if (userInfo.isEmpty || !userInfo.map(_.id).contains(name)) {
        userInfo = userInfo :+ user.getUserInfo(name)
      }
      val userToPrint = if(userInfo.map(_.id).contains(name)) userInfo.filter(_.id == name).head else return println("User not found")
      println("----------------------------------------")
      println(s"$YELLOW${userToPrint.id} $RESET")
      println(s"Description: $WHITE${replaceHtmlEntities(userToPrint.about)}$RESET")
      println(s"Created at: $WHITE${unixTimeToDate(userToPrint.created)}$RESET")
      println(s"Karma: $WHITE${userToPrint.karma}$RESET")
      println(s"Submitted items: $WHITE${userToPrint.submitted.length}$RESET")
      println("")
    }
}
