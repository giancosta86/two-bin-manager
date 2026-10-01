package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetNumberGroupingProvider


class AverageTargetByInitialFrameWidth extends AverageTargetNumberGroupingProvider(
  "Initial frame width",
  solution =>
    solution.problem.frameTemplate.initialDimension.width
)
