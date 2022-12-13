package cz.cvut.fit.bioop.hackernewsclient
import scala.Console._

class UserInfoProxy extends Item{
    val user = new UserInfo()
    var userInfo: List[User] = List()
    override def displayItem(name: String): Unit = {
      if (userInfo.isEmpty || !userInfo.map(_.id).contains(name)) {
        userInfo = userInfo :+ user.getUserInfo(name)
      }
      var userToPrint = userInfo.map(user => {
        if (user.id == name) {
          println("----------------------------------------")
          println(s"$YELLOW${user.id} $RESET")
          println(s"Description: $BLUE${user.about} $RESET")
          println(s"Created at: $BLUE${unixTimeToDate(user.created)} $RESET")
          println(s"Karma: $BLUE${user.karma}$RESET")
          println(s"Submitted items: $BLUE${user.submitted.length}$RESET")
          println("")
        }
      })
    }

  def unixTimeToDate(unixTime: Long): String = {
    val date = new java.util.Date(unixTime * 1000L)
    val sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"))
    sdf.format(date)
  }
}
