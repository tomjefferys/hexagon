package hexagon

import hexagon.Slopes.Slope

/**
  * A component of the hexagon
  * Either a space in the middle of the hexagon
  * or an edge between two hexagons
  */
sealed abstract class HexComponent

/**
  * the inside area of a hexagon
  * @param location
  */
case class Interior(location: HexLocation) extends HexComponent

/**
  * The different types of slope in a hexagon
  */
object Slopes {
  sealed abstract class Slope(val ch: Char)

  case object LEFT_SLOPE extends Slope('\\')
  case object RIGHT_SLOPE extends Slope('/')
  case object HORIZONTAL extends Slope('_')
}

/**
  * The edge of a hexagon
  * @param slope
  * @param hex1
  * @param hex2
  */
case class Edge(slope: Slope, hex1: HexLocation, hex2: HexLocation) extends HexComponent
