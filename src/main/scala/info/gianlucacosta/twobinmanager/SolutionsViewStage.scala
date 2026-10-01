package info.gianlucacosta.twobinmanager

import java.time.Duration
import javafx.beans.property.SimpleObjectProperty

import info.gianlucacosta.helios.Includes._
import info.gianlucacosta.helios.apps.AppInfo
import info.gianlucacosta.helios.fx.Includes._
import info.gianlucacosta.helios.fx.stage.StackedStage
import info.gianlucacosta.twobinmanager.util.BasicFormTextField
import info.gianlucacosta.twobinpack.core.{Problem, Solution}
import info.gianlucacosta.twobinpack.rendering.frame.Frame
import info.gianlucacosta.twobinpack.rendering.frame.axes.AxesPane

import scalafx.Includes._
import scalafx.beans.binding.Bindings
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{ListView, ScrollPane}
import scalafx.scene.layout.{BorderPane, HBox}

/**
  * Stage showing solutions for a problem
  *
  * @param appInfo       App info
  * @param previousStage The previous stage
  * @param solutions     The solutions for the same problem
  */
class SolutionsViewStage(appInfo: AppInfo, val previousStage: javafx.stage.Stage, solutions: Iterable[Solution]) extends StackedStage {
  this.setMainIcon(appInfo)

  require(
    solutions.nonEmpty,
    "There must be at least one solution"
  )

  private val problem: Problem =
    solutions.head.problem

  require(
    solutions.forall(_.problem == problem),
    "The solutions must refer all to the same problem"
  )

  private val sortedSolutions: List[Solution] =
    solutions
      .toList
      .sorted

  private val solutionsListView =
    new ListView[Solution](sortedSolutions) {
      prefWidth =
        350

      padding =
        Insets(5)
    }


  solutionsListView.selectionModel.value.selectedItem.onChange {
    val solution =
      solutionsListView.selectionModel.value.selectedItem()

    if (solution != null) {
      frame.clearBlocks()

      solution.blocks.foreach(block => {
        frame.addBlock(block)
      })
    }
  }


  private val frame = new Frame(
    problem.frameTemplate
  ) {
    interactive =
      false
  }

  private val frameScrollPane =
    new ScrollPane {
      content =
        new AxesPane(
          frame
        )
    }


  private val targetOption =
    new SimpleObjectProperty[Option[Int]](None)

  targetOption <==
    Bindings.createObjectBinding[Option[Int]](
      () => {
        val solution =
          solutionsListView.selectionModel.value.selectedItem()

        if (solution != null)
          solution.target
        else
          None
      },

      solutionsListView.selectionModel.value.selectedItem
    )


  private val elapsedTimeOption =
    new SimpleObjectProperty[Option[Duration]](None)

  elapsedTimeOption <==
    Bindings.createObjectBinding[Option[Duration]](
      () => {
        val solution =
          solutionsListView.selectionModel.value.selectedItem()

        if (solution != null)
          solution.elapsedTimeOption
        else
          None
      },

      solutionsListView.selectionModel.value.selectedItem
    )


  private val solutionPane =
    new BorderPane {
      top =
        new HBox {
          padding =
            Insets(20)


          spacing =
            20

          children =
            List(
              new BasicFormTextField("Target:") {
                fieldValue <==
                  Bindings.createStringBinding(
                    () => Solution.formatTarget(
                      targetOption()
                    ),

                    targetOption
                  )

                visible <==
                  solutionsListView.selectionModel.value.selectedItem =!= null
              },


              new BasicFormTextField("Time elapsed:") {
                fieldValue <==
                  Bindings.createStringBinding(
                    () => {
                      elapsedTimeOption()
                        .map(elapsedTime =>
                          elapsedTime.digitalFormat
                        )
                        .getOrElse(
                          "(not available)"
                        )
                    },

                    elapsedTimeOption
                  )

                visible <==
                  solutionsListView.selectionModel.value.selectedItem =!= null
              },


              new BasicFormTextField("Time limit:") {
                fieldValue =
                  problem.timeLimitOption
                    .map(timeLimit =>
                      timeLimit.digitalFormat
                    )
                    .getOrElse("(no time limit)")

                visible <==
                  solutionsListView.selectionModel.value.selectedItem =!= null
              }
            )
        }

      center =
        frameScrollPane

    }


  scene = new Scene {
    root =
      new BorderPane {
        left =
          solutionsListView

        center =
          solutionPane
      }
  }

  maximized =
    true

  title =
    s"View solutions for problem '${problem.name}'..."
}
