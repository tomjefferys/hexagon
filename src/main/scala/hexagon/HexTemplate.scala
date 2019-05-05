package hexagon

import hexagon.HexTemplate.LOCATIONS
import hexagon.Slopes.{HORIZONTAL, LEFT_SLOPE, RIGHT_SLOPE}

import scala.collection.mutable.ArrayBuffer

object HexTemplate {

  // The relative locations of the hexagons in the template
  private val LOCATIONS = Map(
    'A' -> (-1,-1),
    'B' -> ( 0,-2),
    'C' -> ( 1,-1),
    'D' -> ( 0, 0),
    'E' -> (-1, 1),
    'F' -> ( 1, 1))

  val SMALL = HexTemplate(
    Array(
      """\__/  """.toCharArray,
      """/  \__""".toCharArray
    ),
    Array(
      "AB,BD,BD,BC,CC,CC".split(','),
      "AD,DD,DD,DC,CF,CF".split(',')
    )
  )

  val MEDIUM = HexTemplate(
    Array(
      """ \___/    """.toCharArray,
      """ /   \    """.toCharArray,
      """/     \___""".toCharArray,
      """\     /   """.toCharArray
    ),
    Array(
      "AA,AB,BD,BD,BD,BC,CC,CC,CC,CC".split(','),
      "AA,AD,DD,DD,DD,DC,CC,CC,CC,CC".split(','),
      "AD,DD,DD,DD,DD,DD,DC,CF,CF,CF".split(','),
      "ED,DD,DD,DD,DD,DD,DF,FF,FF,FF".split(',')
    )
  )
}

case class HexTemplate(
  template: Array[Array[Char]],
  labels: Array[Array[String]]) {

  val height = template.length
  val width = template(0).length

  private val components = makeComponents()

  // Combine TEMPLATE with LABELED TEMPLATE to create array of HexComponents
  private def makeComponents() : Array[Array[HexComponent]] = {
    val components = ArrayBuffer[Array[HexComponent]]()
    for (y <- 0 until height) {
      val row = ArrayBuffer[HexComponent]()
      for (x <- 0 until width) {
        val hexes = labels(y)(x)
        val hex0 = LOCATIONS(hexes(0))
        val hex1 = LOCATIONS(hexes(1))
        row += (
          template(y)(x) match {
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

  def apply(location: Location) : HexComponent = components(location.x)(location.y)

}
