package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetNumberGroupingProvider


class AverageTargetByTotalBlockCount extends AverageTargetNumberGroupingProvider(
  "Total block count",
  solution =>
    solution.problem.frameTemplate.blockPool.totalBlockCount
)
