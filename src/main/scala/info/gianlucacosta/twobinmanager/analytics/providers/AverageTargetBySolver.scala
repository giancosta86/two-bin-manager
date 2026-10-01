package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetStringGroupingProvider

class AverageTargetBySolver extends AverageTargetStringGroupingProvider(
  "Solver",
  solution =>
    solution.solverOption.getOrElse("(anonymous)")
)