package hexagon

/**
  * A coordinate identifying a hexagon on a grid
  * Uses a doubled coordinate scheme:
  *   ___       ___       ___
  *  /   \     /   \     /   \
  * / 0,0 \___/ 2,0 \___/ 4,0 \
  * \     /   \     /   \     /
  *  \___/ 1,1 \___/ 3,1 \___/
  *  /   \     /   \     /   \
  * / 0,2 \___/ 2,2 \___/ 4,2 \
  * \     /   \     /   \     /
  *  \___/     \___/     \___/
  *
  * So not all coordinate pairs are valid
  * @param x
  * @param y
  */
object HexLocation {
  implicit def toHexLocation(xy: (Int, Int)) = new HexLocation(xy._1, xy._2)

  def isValid(x: Int, y: Int) : Boolean = !(y%2 == 0 ^ x%2 == 0)
}

class HexLocation(val x: Int, val y: Int) {

  if (y%2 == 0 ^ x%2 == 0)
  {
    throw new IllegalArgumentException(s"Invalid coordinates: ($x,$y)")
  }

  def +(that: (Int, Int)) : HexLocation = (x + that._1, y + that._2)

  // TODO make a location type
  def getGridLocation() : ((Int, Int), (Int, Int)) = {
    val startX = x * 5
    val startY = y * 2
    val endX = startX + 6
    val endY = startY + 4
    return ((startX, startY), (endX, endY))
  }

  override def toString: String = s"($x,$y)"


  def canEqual(other: Any): Boolean = other.isInstanceOf[HexLocation]

  override def equals(other: Any): Boolean = other match {
    case that: HexLocation =>
      (that canEqual this) &&
        x == that.x &&
        y == that.y
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(x, y)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}


