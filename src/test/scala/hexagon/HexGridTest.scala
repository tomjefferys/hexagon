package hexagon


import org.scalatest._

class HexGridTest extends FlatSpec with Matchers {

   val TRAILING_SPACE_REGEX = """(?m)\s+$"""

  "A HexGrid" should "generate a 1x1 dimension grid" in {
    val hexGrid = HexGrid.getGrid(1,1)
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
    val hexGrid = HexGrid.getGrid(2,2)
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
    val hexGrid = HexGrid.getGrid(2,3)
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
    val hexGrid = HexGrid.getGrid(3, 2)
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
    val hexGrid = HexGrid.getGrid(3,3)
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
    val hexGrid = HexGrid.getGrid(3,4)
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
    val hexGrid = HexGrid.getGrid(4,3)
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
    val hexGrid = HexGrid.getGrid(4,4)
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
}
