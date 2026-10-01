package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetNumberGroupingProvider


class AverageTargetByInitialFrameArea extends AverageTargetNumberGroupingProvider(
  "Initial frame area",
  solution =>
    solution.problem.frameTemplate.initialDimension.area
)
