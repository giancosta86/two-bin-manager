package info.gianlucacosta.twobinmanager.generators.binpacking

import javafx.stage.Stage

import info.gianlucacosta.twobinmanager.sdk.generators.GeneratorFxmlStage
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository


private class BinPackingStage(previousStage: Stage, problemRepository: ProblemRepository)
  extends GeneratorFxmlStage[BinPackingController, javafx.scene.layout.BorderPane](
    classOf[BinPackingController],
    previousStage,
    problemRepository,
    BinPackingGenerator.Name
  )