package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.helios.fx.util.FxEngine
import info.gianlucacosta.twobinpack.core._
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

import scalafx.Includes._
import scalafx.scene.chart.XYChart

class TestAverageTargetByInitialFrameArea extends FlatSpec with Matchers with BeforeAndAfterEach {

  override protected def beforeEach(): Unit = {
    FxEngine.initialize()
  }

  private val frameTemplate =
    FrameTemplate(
      FrameDimension(
        8,
        10
      ),

      FrameMode.Strip,

      BlockPool.create(
        false,
        BlockDimension(
          1,
          1
        ) -> 2
      ),

      FrameTemplate.SuggestedBlockColorsPool,

      30
    )

  private val problem =
    Problem(
      frameTemplate,
      None,
      "TestProblem"
    )


  private val solutions =
    List(
      Solution(
        problem,
        None,
        None,

        Set(
          AnchoredBlock(
            BlockDimension(
              1,
              1
            ),

            QuantizedPoint2D(
              0,
              2
            )
          ),

          AnchoredBlock(
            BlockDimension(
              1,
              1
            ),

            QuantizedPoint2D(
              5,
              4
            )
          )
        )
      ),


      Solution(
        problem,
        None,
        None,

        Set()
      ),


      Solution(
        problem,
        None,
        None,

        Set(
          AnchoredBlock(
            BlockDimension(
              1,
              1
            ),

            QuantizedPoint2D(
              2,
              3
            )
          ),

          AnchoredBlock(
            BlockDimension(
              1,
              1
            ),

            QuantizedPoint2D(
              7,
              4
            )
          )
        )
      )
    )


  "Solution values" should "be correct" in {
    solutions.map(_.target) should be(
      List(
        Some(6),
        None,
        Some(8)
      )
    )
  }


  "Analytics dimensions" should "be correct" in {
    val provider =
      new AverageTargetByInitialFrameArea

    provider.analyticsDimensions.keySet should be(Set(
      "Average target by initial frame area",
      "Average target by initial frame area - Knapsack",
      "Average target by initial frame area - Strip"
    )
    )
  }


  "The global chart data" should "be correct" in {
    val provider =
      new AverageTargetByInitialFrameArea

    val chartRetriever =
      provider.analyticsDimensions(
        "Average target by initial frame area"
      )


    val chart: XYChart[Number, Number] =
      chartRetriever(
        solutions,
        Map()
      )._1.get.asInstanceOf[XYChart[Number, Number]]


    val chartValues: Set[(Number, Number)] =
      getChartValues(chart)


    chartValues should be(
      Set(
        80 -> 7
      )
    )
  }


  "The Knapsack chart data" should "be None" in {
    val provider =
      new AverageTargetByInitialFrameArea

    val chartRetriever =
      provider.analyticsDimensions(
        "Average target by initial frame area - Knapsack"
      )


    val chart =
      chartRetriever(
        solutions,
        Map()
      )._1


    chart should be(None)
  }


  "The Strip chart data" should "be correct" in {
    val provider =
      new AverageTargetByInitialFrameArea

    val chartRetriever =
      provider.analyticsDimensions(
        "Average target by initial frame area - Strip"
      )


    val chart: XYChart[Number, Number] =
      chartRetriever(
        solutions,
        Map()
      )._1.get.asInstanceOf[XYChart[Number, Number]]


    val chartValues: Set[(Number, Number)] =
      getChartValues(chart)


    chartValues should be(
      Set(
        80 -> 7
      )
    )
  }


  private def getChartValues(chart: XYChart[Number, Number]): Set[(Number, Number)] = {
    val dataSet: Set[javafx.scene.chart.XYChart.Data[Number, Number]] =
      chart
        .data()
        .get(0)
        .data()
        .toSet


    dataSet
      .map(item =>
        (item.XValue(), item.YValue())
      )
  }
}
