package info.gianlucacosta.twobinmanager.importers

import java.io.{BufferedReader, File, FileReader}

import info.gianlucacosta.twobinmanager.sdk.importers.ProblemImporter
import info.gianlucacosta.twobinmanager.sdk.io.OutputWriter
import info.gianlucacosta.twobinpack.core.Problem
import info.gianlucacosta.twobinpack.io.bundle.ProblemBundleReader

/**
  * Base class for importing a ProblemBundle using the dedicated reader
  */
abstract class ProblemBundleImporterBase extends ProblemImporter {
  override protected def readProblems(file: File, outputWriter: OutputWriter): Set[Problem] = {
    val sourceReader =
      new BufferedReader(
        new FileReader(file)
      )

    readProblemsInBundle(sourceReader)
  }

  /**
    * Employs a ProblemBundleReader to read a ProblemBundle and get its set of problems
    *
    * @param sourceReader The source reader
    * @return The set of problems in the parsed problem bundle
    */
  protected def readProblemsInBundle(sourceReader: BufferedReader): Set[Problem] = {
    val problemBundleReader =
      new ProblemBundleReader(sourceReader)

    try {
      val problemBundle =
        problemBundleReader.readProblemBundle()

      problemBundle.problems.toSet
    } finally {
      problemBundleReader.close()
    }
  }
}
