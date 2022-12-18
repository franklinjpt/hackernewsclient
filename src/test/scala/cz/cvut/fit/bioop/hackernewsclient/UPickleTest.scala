//package cz.cvut.fit.bioop.hackernewsclient
//
//import org.scalatest.flatspec.AnyFlatSpec
//import org.scalatest.matchers.should
//
//class UPickleTest extends AnyFlatSpec with should.Matchers {
//  // Custom type definition
//  case class BoxedInt(inner: Int)
//
//  // Definition of custom reader
//  import upickle.default._
//  implicit val boxIntReader: Reader[BoxedInt] = reader[Int].map(BoxedInt)
//
//  "A BoxedInt uPickle reader" should "parse list of Ints into list of BoxedInts" in {
//    // Define json
//    val json = "[1, 2, 3, 4, 5]"
//
//    // Parse json
//    val result: List[BoxedInt] = read[List[BoxedInt]](json)
//
//    // Assert result
//    result shouldEqual List(
//      BoxedInt(1),
//      BoxedInt(2),
//      BoxedInt(3),
//      BoxedInt(4),
//      BoxedInt(5))
//  }
//
//  "A Story uPickle"
//}
