package info.gianlucacosta.twobinmanager.generators.guillotine

import javafx.stage.Stage

import info.gianlucacosta.twobinmanager.sdk.generators.ProblemGenerator
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

object GuillotineGenerator {
  val Name: String =
    "Guillotine random generator"
}

/**
  * Guillotine random generator
  */
class GuillotineGenerator extends ProblemGenerator {
  override def generate(previousStage: Stage, problemRepository: ProblemRepository): Unit = {
    val guillotineStage =
      new GuillotineStage(previousStage, problemRepository)

    guillotineStage.show()
  }

  override def name: String =
    GuillotineGenerator.Name
}
