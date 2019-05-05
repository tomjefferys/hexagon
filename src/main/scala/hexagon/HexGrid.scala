package hexagon

import java.lang.Math.{max, min}

import hexagon.Slopes.{HORIZONTAL, LEFT_SLOPE, RIGHT_SLOPE}

import scala.collection.mutable.ArrayBuffer
import scala.util.Properties

object HexGrid {

  def printHex(rows: Int, cols: Int, template: HexTemplate): Unit = {
    println(getGrid(rows, cols, template))
  }

  // Get a string view of the hex grid for the supplied set of hexagons
  def getGrid(hexes : Set[HexLocation], template: HexTemplate) : String = {
    // get lists of minimum and maximum locations for each hex
    val minLocs = hexes.map(h => h.getGridLocation()._1)
    val maxLocs = hexes.map(h => h.getGridLocation()._2)

    // Find the overall minimum location
    val minLoc = (minLocs.foldLeft(Int.MaxValue) ((acc, loc) => min(acc, loc.x)),
                  minLocs.foldLeft(Int.MaxValue) ((acc, loc) => min(acc, loc.y)))

    // Find the overall maximum location
    val maxLoc = (maxLocs.foldLeft(Int.MinValue) ((acc, loc) => max(acc, loc.x)),
                  maxLocs.foldLeft(Int.MinValue) ((acc, loc) => max(acc, loc.y)))

    val str = new StringBuilder
    for(y <- minLoc._2 to maxLoc._2) {
      for(x <- minLoc._1 to maxLoc._1) {
        val location = (2 * (x/template.width), 2 * (y/template.height)) // The template location
        val component = template(y % template.height, x % template.width) // The component within the template
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
      str ++= Properties.lineSeparator
    }
    return str.toString()
  }

  // Get a string view of a rectangle of hexagons
  def getGrid(rows: Int, cols: Int, template: HexTemplate) : String = {
    val hexes = for {
        row <- 0 to (rows - 1)
        col <- (row %2) to (cols - 1) by 2
    } yield new HexLocation(col, row)
    return getGrid(hexes.toSet, template)
  }

}

class HexGrid {

}
