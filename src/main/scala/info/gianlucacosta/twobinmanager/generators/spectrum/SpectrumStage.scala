package info.gianlucacosta.twobinmanager.generators.spectrum

import javafx.stage.Stage

import info.gianlucacosta.twobinmanager.sdk.generators.GeneratorFxmlStage
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

/**
  * Spectrum generator's stage
  *
  * @param previousStage     The previous stage
  * @param problemRepository The problem repository
  */
private class SpectrumStage(previousStage: Stage, problemRepository: ProblemRepository)
  extends GeneratorFxmlStage[SpectrumController, javafx.scene.layout.BorderPane](
    classOf[SpectrumController],
    previousStage,
    problemRepository,
    SpectrumGenerator.Name
  )