package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetStringGroupingProvider

class AverageTargetByRotationEnabled extends AverageTargetStringGroupingProvider(
  "Rotation enabled",
  solution =>
    solution.problem.frameTemplate.blockPool.canRotateBlocks.toString
)