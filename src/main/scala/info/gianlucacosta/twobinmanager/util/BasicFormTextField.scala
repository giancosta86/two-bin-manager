package info.gianlucacosta.twobinmanager.util

import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}

import scalafx.Includes._
import scalafx.beans.binding.Bindings
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.HBox

/**
  * Shows a description field (in bold font) followed by a value field
  *
  * @param fieldDescription  The field's description (shown by the leftmost label)
  * @param initialFieldValue The field's value
  * @param internalMargin    The space between description and value
  */
class BasicFormTextField(
                          fieldDescription: String,
                          initialFieldValue: Any = "",
                          internalMargin: Int = 5
                        ) extends HBox {

  styleClass +=
    "basicFormTextField"


  private val _fieldValue =
    new SimpleObjectProperty[Any](initialFieldValue)

  /**
    * The field value property, which updated the related label
    *
    * @return
    */
  def fieldValue: ObjectProperty[Any] =
    _fieldValue

  def fieldValue_=(newValue: Any): Unit =
    _fieldValue() =
      newValue


  children = List(
    new Label {
      text =
        fieldDescription

      margin =
        Insets(0, internalMargin, 0, 0)

      style =
        "-fx-font-weight: bold;"
    },

    new Label {
      text <==
        Bindings.createStringBinding(
          () =>
            fieldValue().toString,

          fieldValue
        )
    }
  )
}
