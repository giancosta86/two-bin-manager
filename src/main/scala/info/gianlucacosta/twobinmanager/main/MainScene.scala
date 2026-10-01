package info.gianlucacosta.twobinmanager.main

import javafx.stage.Stage

import info.gianlucacosta.helios.apps.AppInfo
import info.gianlucacosta.helios.fx.scene.fxml.FxmlScene
import info.gianlucacosta.twobinmanager.AppDb

/**
  * Scene displayed in the primary stage
  *
  * @param appInfo Application info
  * @param stage   The stage
  */
class MainScene(appInfo: AppInfo, stage: Stage) extends FxmlScene[MainController, javafx.scene.layout.BorderPane](
  classOf[MainController]
) {
  override protected def preInitialize(): Unit = {
    controller.appInfo =
      appInfo

    controller.stage =
      stage

    controller.problemRepository =
      AppDb.problemRepository

    controller.solutionRepository =
      AppDb.solutionRepository
  }
}
