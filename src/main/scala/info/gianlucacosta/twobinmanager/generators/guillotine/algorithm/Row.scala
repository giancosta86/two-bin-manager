package info.gianlucacosta.twobinmanager.generators.guillotine.algorithm

/**
  * A row created by the Guillotine generator
  *
  * @param top    The 0-based quantized top of the row
  * @param height The quantized height of the row
  */
private case class Row(
                        top: Int,
                        height: Int
                      )
