package info.gianlucacosta.twobinmanager.generators.guillotine.algorithm

/**
  * A column within a row created by the Guillotine generator
  *
  * @param row   The parent row
  * @param left  The 0-based quantized left of the column
  * @param width The quantized width of the column
  */
private case class Column(
                           row: Row,
                           left: Int,
                           width: Int
                         )