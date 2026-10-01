package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetNumberGroupingProvider

class AverageTargetByBlockDimensions extends AverageTargetNumberGroupingProvider(
  "Total block dimensions",
  solution =>
    solution.problem.frameTemplate.blockPool.blockDimensions.size
)