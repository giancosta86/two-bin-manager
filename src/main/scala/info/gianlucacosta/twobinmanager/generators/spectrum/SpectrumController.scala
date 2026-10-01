package info.gianlucacosta.twobinmanager.generators.spectrum

import javafx.fxml.FXML

import info.gianlucacosta.twobinmanager.generators.spectrum.algorithm.SpectrumAlgorithm
import info.gianlucacosta.twobinmanager.generators.spectrumbase.SpectrumControllerBase
import info.gianlucacosta.twobinpack.core._

import scalafx.Includes._

private class SpectrumController extends SpectrumControllerBase {

  private class SpectrumTask extends BlockPoolTask {
    override def call(): BlockPool = {
      val minBlockWidth =
        minBlockWidthField.text().toInt

      val minBlockHeight =
        minBlockHeightField.text().toInt

      val minBlockDimension =
        BlockDimension(
          minBlockWidth,
          minBlockHeight
        )


      val maxBlockWidth =
        maxBlockWidthField.text().toInt

      val maxBlockHeight =
        maxBlockHeightField.text().toInt

      val maxBlockDimension =
        BlockDimension(
          maxBlockWidth,
          maxBlockHeight
        )


      val minBlockQuantity =
        minBlockQuantityField.text().toInt

      val maxBlockQuantity =
        maxBlockQuantityField.text().toInt


      val blockQuantityRange =
        Range.inclusive(minBlockQuantity, maxBlockQuantity)

      val canRotateBlocks =
        canRotateCheckBox.isSelected

      SpectrumAlgorithm.createRandomPool(
        minBlockDimension,
        maxBlockDimension,
        blockQuantityRange,
        canRotateBlocks
      )
    }
  }


  override protected def createBlockPoolTask(): BlockPoolTask =
    new SpectrumTask


  @FXML
  var canRotateCheckBox: javafx.scene.control.CheckBox = _


  @FXML
  var minBlockWidthField: javafx.scene.control.TextField = _

  @FXML
  var minBlockHeightField: javafx.scene.control.TextField = _


  @FXML
  var maxBlockWidthField: javafx.scene.control.TextField = _

  @FXML
  var maxBlockHeightField: javafx.scene.control.TextField = _


  @FXML
  var minBlockQuantityField: javafx.scene.control.TextField = _

  @FXML
  var maxBlockQuantityField: javafx.scene.control.TextField = _
}
