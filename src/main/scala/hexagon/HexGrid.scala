package hexagon

import java.lang.Math.{max, min}

import hexagon.Slopes.{HORIZONTAL, LEFT_SLOPE, RIGHT_SLOPE}

import scala.collection.mutable.ArrayBuffer

object HexGrid {

  // The basic text based structure of a hexagon grid
  val TEMPLATE = Array(
    """ \___/    """.toCharArray,
    """ /   \    """.toCharArray,
    """/     \___""".toCharArray,
    """\     /   """.toCharArray
  )

  val TEMPLATE_HEIGHT = TEMPLATE.length
  val TEMPLATE_WIDTH = TEMPLATE(0).length

  // Map with different hexagons highlighted
  val LABELED_TEMPLATE = Array(
    "AA,AB,BD,BD,BD,BC,CC,CC,CC,CC".split(','),
    "AA,AD,DD,DD,DD,DC,CC,CC,CC,CC".split(','),
    "AD,DD,DD,DD,DD,DD,DC,CF,CF,CF".split(','),
    "ED,DD,DD,DD,DD,DD,DF,FF,FF,FF".split(',')
  )

  // The relative locations of the hexagons in the template
  val LOCATIONS = Map(
      'A' -> (-1,-1),
      'B' -> ( 0,-2),
      'C' -> ( 1,-1),
      'D' -> ( 0, 0),
      'E' -> (-1, 1),
      'F' -> ( 1, 1))

  val COMPONENTS = makeComponents()

  // Combine TEMPLATE with LABELED TEMPLATE to create array of HexComponents
  private def makeComponents() : Array[Array[HexComponent]] = {
    val components = ArrayBuffer[Array[HexComponent]]()
    for (y <- 0 until TEMPLATE_HEIGHT) {
      val row = ArrayBuffer[HexComponent]()
      for (x <- 0 until TEMPLATE_WIDTH) {
        val hexes = LABELED_TEMPLATE(y)(x)
        val hex0 = LOCATIONS(hexes(0))
        val hex1 = LOCATIONS(hexes(1))
        row += (
          TEMPLATE(y)(x) match {
            case ' ' => Interior(LOCATIONS(hexes(0)))
            case '\\' => Edge(LEFT_SLOPE, hex0, hex1)
            case '_' => Edge(HORIZONTAL, hex0, hex1)
            case '/' => Edge(RIGHT_SLOPE, hex0, hex1)
          }
        )
      }
      components += row.toArray
    }
    return components.toArray
  }


  def printHex(rows: Int, cols: Int): Unit = {
    println(getGrid(rows, cols))
  }

  // Get a string view of the hex grid for the supplied set of hexagons
  def getGrid(hexes : Set[HexLocation]) : String = {
    // get lists of minimum and maximum locations for each hex
    val minLocs = hexes.map(h => h.getGridLocation()._1)
    val maxLocs = hexes.map(h => h.getGridLocation()._2)

    // Find the overall minimum location
    val minLoc = (minLocs.foldLeft(Int.MaxValue) ((acc, loc) => min(acc, loc._1)),
                  minLocs.foldLeft(Int.MaxValue) ((acc, loc) => min(acc, loc._2)))

    // Find the overall maximum location
    val maxLoc = (maxLocs.foldLeft(Int.MinValue) ((acc, loc) => max(acc, loc._1)),
                  maxLocs.foldLeft(Int.MinValue) ((acc, loc) => max(acc, loc._2)))

    val str = new StringBuilder
    for(y <- minLoc._2 to maxLoc._2) {
      for(x <- minLoc._1 to maxLoc._1) {
        val location = (2 * (x/TEMPLATE_WIDTH), 2 * (y/TEMPLATE_HEIGHT)) // The template location
        val component = COMPONENTS(y % TEMPLATE_HEIGHT)(x % TEMPLATE_WIDTH) // The component within the template
        str += (component match {
          case Interior(_) => ' '
          case Edge(slope, hex1, hex2) => {
            if (hexes.contains(hex1 + location) ||
                hexes.contains(hex2 + location))
                slope.ch
              else
                ' '
          }
        })
      }
      str += '\n'
    }
    return str.toString()
  }

  // Get a string view of a rectangle of hexagons
  def getGrid(rows: Int, cols: Int) : String = {
    val hexes = for {
        row <- 0 to (rows - 1)
        col <- (row %2) to (cols - 1) by 2
    } yield new HexLocation(col, row)
    return getGrid(hexes.toSet)
  }

}

class HexGrid {

}
