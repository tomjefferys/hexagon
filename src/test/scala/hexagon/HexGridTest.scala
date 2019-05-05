package hexagon


import hexagon.HexTemplate.MEDIUM
import org.scalatest._

class HexGridTest extends FlatSpec with Matchers {

   val TRAILING_SPACE_REGEX = """(?m)\s+$"""

  "A HexGrid" should "generate a 1x1 dimension grid" in {
    val hexGrid = HexGrid.getGrid(1,1, MEDIUM)
                         .replaceAll(TRAILING_SPACE_REGEX, "");

    hexGrid should be (
      """  ___
        | /   \
        |/     \
        |\     /
        | \___/""".stripMargin
    )
  }

  it should "generate a 2x2 dimension grid" in {
    val hexGrid = HexGrid.getGrid(2,2, MEDIUM)
      .replaceAll(TRAILING_SPACE_REGEX, "")

    hexGrid should be (
      """  ___
        | /   \
        |/     \___
        |\     /   \
        | \___/     \
        |     \     /
        |      \___/""".stripMargin
    )
  }

  it should "generate a 2x3 dimension grid" in {
    val hexGrid = HexGrid.getGrid(2,3, MEDIUM)
      .replaceAll(TRAILING_SPACE_REGEX, "")

    hexGrid should be (
      """  ___       ___
        | /   \     /   \
        |/     \___/     \
        |\     /   \     /
        | \___/     \___/
        |     \     /
        |      \___/""".stripMargin
    )
  }

  it should "generate a 3x2 dimension grid" in {
    val hexGrid = HexGrid.getGrid(3, 2, MEDIUM)
      .replaceAll(TRAILING_SPACE_REGEX, "")

    hexGrid should be(
      """  ___
        | /   \
        |/     \___
        |\     /   \
        | \___/     \
        | /   \     /
        |/     \___/
        |\     /
        | \___/""".stripMargin
    )
  }

   it should "generate a 3x3 dimension grid" in {
    val hexGrid = HexGrid.getGrid(3,3, MEDIUM)
      .replaceAll(TRAILING_SPACE_REGEX, "")

    hexGrid should be (
      """  ___       ___
        | /   \     /   \
        |/     \___/     \
        |\     /   \     /
        | \___/     \___/
        | /   \     /   \
        |/     \___/     \
        |\     /   \     /
        | \___/     \___/""".stripMargin
    )
  }

  it should "generate a 3x4 dimension grid" in {
    val hexGrid = HexGrid.getGrid(3,4, MEDIUM)
      .replaceAll(TRAILING_SPACE_REGEX, "")

    hexGrid should be (
      """  ___       ___
        | /   \     /   \
        |/     \___/     \___
        |\     /   \     /   \
        | \___/     \___/     \
        | /   \     /   \     /
        |/     \___/     \___/
        |\     /   \     /
        | \___/     \___/""".stripMargin
    )
  }

  it should "generate a 4x3 dimension grid" in {
    val hexGrid = HexGrid.getGrid(4,3, MEDIUM)
      .replaceAll(TRAILING_SPACE_REGEX, "")

    hexGrid should be (
      """  ___       ___
        | /   \     /   \
        |/     \___/     \
        |\     /   \     /
        | \___/     \___/
        | /   \     /   \
        |/     \___/     \
        |\     /   \     /
        | \___/     \___/
        |     \     /
        |      \___/""".stripMargin
    )
  }

  it should "generate a 4x4 dimension grid" in {
    val hexGrid = HexGrid.getGrid(4,4, MEDIUM)
      .replaceAll(TRAILING_SPACE_REGEX, "")

    hexGrid should be (
      """  ___       ___
        | /   \     /   \
        |/     \___/     \___
        |\     /   \     /   \
        | \___/     \___/     \
        | /   \     /   \     /
        |/     \___/     \___/
        |\     /   \     /   \
        | \___/     \___/     \
        |     \     /   \     /
        |      \___/     \___/""".stripMargin
    )
  }

  it should "generate a 2x2 grid when give correct hexes" in {
    val hexGrid = HexGrid.getGrid(Set((0,0), (1,1), (0,2), (1,3)), MEDIUM)
                         .replaceAll(TRAILING_SPACE_REGEX, "")
    println(hexGrid)

    hexGrid should be (
      """  ___
        | /   \
        |/     \___
        |\     /   \
        | \___/     \
        | /   \     /
        |/     \___/
        |\     /   \
        | \___/     \
        |     \     /
        |      \___/""".stripMargin
    )

  }

  it should "be able to generate disconnected hexes" in {
    val hexGrid = HexGrid.getGrid(Set((0,0), (2,0), (0,4), (2,4)), MEDIUM)
                         .replaceAll(TRAILING_SPACE_REGEX, "")
    println(hexGrid)
    hexGrid should be (  // Not the actual output, the trailing space regex is trashing empty lines
      """  ___       ___
        | /   \     /   \
        |/     \   /     \
        |\     /   \     /
        | \___/     \___/
        |  ___       ___
        | /   \     /   \
        |/     \   /     \
        |\     /   \     /
        | \___/     \___/""".stripMargin)
  }
}
