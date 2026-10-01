package info.gianlucacosta.twobinmanager.analytics.providers

import info.gianlucacosta.twobinmanager.sdk.analytics.filteringby.framemode.AverageTargetNumberGroupingProvider


class AverageTargetByTimeLimitInMinutes extends AverageTargetNumberGroupingProvider(
  "Time limit in minutes",
  solution =>
    solution.problem.timeLimitOption
      .map(_.toMinutes.toInt)
      .getOrElse[Int](0)
)
