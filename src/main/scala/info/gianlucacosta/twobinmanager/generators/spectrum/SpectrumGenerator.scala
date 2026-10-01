package info.gianlucacosta.twobinmanager.generators.spectrum

import javafx.stage.Stage

import info.gianlucacosta.twobinmanager.sdk.generators.ProblemGenerator
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

object SpectrumGenerator {
  val Name: String =
    "Spectrum strip random generator"
}


/**
  * Spectrum random generator
  */
class SpectrumGenerator extends ProblemGenerator {
  override def generate(previousStage: Stage, problemRepository: ProblemRepository): Unit = {
    val spectrumStage =
      new SpectrumStage(previousStage, problemRepository)

    spectrumStage.show()
  }


  override def name: String =
    SpectrumGenerator.Name
}
