package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetNumberGroupingProvider


class AverageTargetByFrameHeight extends AverageTargetNumberGroupingProvider(
  "Frame height",
  solution =>
    solution.problem.frameTemplate.initialDimension.height
)
