package info.gianlucacosta.twobinmanager.generators.binpacking

import javafx.stage.Stage

import info.gianlucacosta.twobinmanager.sdk.generators.ProblemGenerator
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository


object BinPackingGenerator {
  val Name: String =
    "Bin packing random generator"
}


class BinPackingGenerator extends ProblemGenerator {
  override def generate(previousStage: Stage, problemRepository: ProblemRepository): Unit = {
    val binPackingStage =
      new BinPackingStage(previousStage, problemRepository)

    binPackingStage.show()
  }

  override def name: String =
    BinPackingGenerator.Name
}
