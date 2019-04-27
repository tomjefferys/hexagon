package hexagon

import org.scalatest.{FlatSpec, Matchers}

class HexLocationTest extends FlatSpec with Matchers {

  "A HexLocation" should "throw exception if bad locations are used " in {
    an [IllegalArgumentException] should be thrownBy(new HexLocation(1,0))
    an [IllegalArgumentException] should be thrownBy(new HexLocation(3,0))
    an [IllegalArgumentException] should be thrownBy(new HexLocation(-1,0))
    an [IllegalArgumentException] should be thrownBy(new HexLocation(0,1))
    an [IllegalArgumentException] should be thrownBy(new HexLocation(-2,1))
  }

  it should "not throw exceptions if it is valid" in {
    new HexLocation(0,0)
    new HexLocation(2,0)
    new HexLocation(-2,0)
    new HexLocation(1,1)
    new HexLocation(-1,1)
    new HexLocation(3,1)
  }

  it should "have accessible x and y values" in {
    val hex1 = new HexLocation(2,4)
    hex1.x should be (2)
    hex1.y should be (4)
  }

}
