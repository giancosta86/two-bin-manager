package info.gianlucacosta.twobinmanager.generators.guillotine

import javafx.stage.Stage

import info.gianlucacosta.twobinmanager.sdk.generators.GeneratorFxmlStage
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

/**
  * Guillotine generator's stage
  *
  * @param previousStage     The previous stage
  * @param problemRepository The problem repository
  */
private class GuillotineStage(previousStage: Stage, problemRepository: ProblemRepository)
  extends GeneratorFxmlStage[GuillotineController, javafx.scene.layout.BorderPane](
    classOf[GuillotineController],
    previousStage,
    problemRepository,
    GuillotineGenerator.Name
  )
