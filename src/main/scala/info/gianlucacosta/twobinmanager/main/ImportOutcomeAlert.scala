package info.gianlucacosta.twobinmanager.main

import info.gianlucacosta.helios.fx.dialogs.Alerts

import scalafx.Includes._
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, Label, TextArea}
import scalafx.scene.layout.{Priority, VBox}

/**
  * Dialog showing the results of the import process
  *
  * @param ownerStage        The owner stage
  * @param importedProblems  The total number of imported problems
  * @param importedSolutions The total number of imported solutions
  * @param importLogString   The overall log string
  */
private class ImportOutcomeAlert(
                                  ownerStage: javafx.stage.Stage,
                                  importedProblems: Long,
                                  importedSolutions: Long,
                                  importLogString: String
                                ) extends Alert(AlertType.Information) {
  initOwner(ownerStage)

  title =
    "Import files..."

  headerText =
    "Import outcome and log"


  val summaryLabel =
    new Label(s"Imported problems: ${importedProblems}\nImported solutions: ${importedSolutions}")

  val logTextArea = new TextArea {
    text =
      importLogString

    editable =
      false

    wrapText =
      false

    minWidth =
      900

    minHeight =
      450

    vgrow =
      Priority.Always

    hgrow =
      Priority.Always

    style =
      "-fx-font-family: monospace;"
  }

  dialogPane().content =
    new VBox {
      spacing =
        20

      children = List(
        summaryLabel,
        logTextArea
      )
    }

  Alerts.fix(this)
}
