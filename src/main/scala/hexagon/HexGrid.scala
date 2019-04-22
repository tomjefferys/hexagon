package hexagon

object HexGrid {

  val HEX = Array(
    """ \___/    """.toCharArray,
    """ /   \    """.toCharArray,
    """/     \___""".toCharArray,
    """\     /   """.toCharArray
  )

  // TODO get content into the hexes
  // TODO Either use substitution,
  // TODO 123
  // TODO abc
  // TODO or just check locations

  val HEX_ROW0 = "  ___     "

  def printHex(rows: Int, cols: Int): Unit = {
//    val hexes = new StringBuilder
//    val lastRow = rows * 2 + 3
//    val lastCol = cols * 5 + 2
//    val isEvenRows = rows % 2 == 0
//    val isEvenCols = cols % 2 == 0
//    // Go through each cell, and display the coresponding HEX cell,
//    // unless we're near the corners and need to special case
//    // to keep things looking tidy
//    for(y <- 0 until lastRow) {
//      for(x <- 0 until lastCol) {
//        val isFirst3Rows = y < 3
//        val isLast2Rows = y >= lastRow - 2
//        val isFirst2Cols = x < 2
//        val isLast2Cols = x >= lastCol - 2
//
//        if (y == 0)
//          hexes += HEX_ROW0(x % 10)
//        else if ((isFirst3Rows && isEvenCols && isLast2Cols) || // Top right
//                (isEvenRows && isLast2Rows && isFirst2Cols) || // Bottom left
//                ((isEvenRows ^ isEvenCols) && isLast2Rows && isLast2Cols)) // Bottom right
//          hexes += ' '
//        else
//          hexes += HEX(y % 4)(x % 10)
//      }
//      hexes.append("\n")
//    }
    println(getGrid(rows, cols))
  }

  def getGrid(rows: Int, cols: Int): String =
  {
    val hexes = new StringBuilder
    val lastRow = rows * 2 + 3
    val lastCol = cols * 5 + 2
    val isEvenRows = rows % 2 == 0
    val isEvenCols = cols % 2 == 0
    // Go through each cell, and display the coresponding HEX cell,
    // unless we're near the corners and need to special case
    // to keep things looking tidy
    for(y <- 0 until lastRow) {
      for(x <- 0 until lastCol) {
        val isFirst3Rows = y < 3
        val isLast2Rows = y >= lastRow - 2
        val isFirst2Cols = x < 2
        val isLast2Cols = x >= lastCol - 2

        if (y == 0)
          hexes += HEX_ROW0(x % 10)
        else if ((isFirst3Rows && isEvenCols && isLast2Cols) || // Top right
          (isEvenRows && isLast2Rows && isFirst2Cols) || // Bottom left
          ((isEvenRows ^ isEvenCols) && isLast2Rows && isLast2Cols)) // Bottom right
          hexes += ' '
        else
          hexes += HEX(y % 4)(x % 10)
      }
      hexes.append("\n")
    }
    return hexes.toString()
  }


}

class HexGrid {

}
