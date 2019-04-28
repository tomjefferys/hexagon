package hexagon

/**
  * A basic location type, representing coordinates on a square grid
  * @param x
  * @param y
  */
case class Location(x: Int, y: Int) {

}

object Location {
  implicit def toLocation(xy : (Int, Int)) : Location = new Location(xy._1, xy._2)
}
