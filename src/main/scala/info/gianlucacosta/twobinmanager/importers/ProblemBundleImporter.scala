package info.gianlucacosta.twobinmanager.importers

import java.io.{BufferedReader, File, StringReader}
import java.nio.charset.Charset
import java.nio.file.Files
import java.util.regex.Pattern

import info.gianlucacosta.twobinmanager.sdk.io.OutputWriter
import info.gianlucacosta.twobinpack.core.Problem
import info.gianlucacosta.twobinpack.io.FileExtensions

import scala.annotation.tailrec


object ProblemBundleImporter {

  private object DurationInMinutesPatterns {
    val someTagPattern =
      Pattern.compile(
        "<timeLimitInMinutesOption class=\"some\">\\s*<x class=\"int\">(\\d+)<\\/x>\\s*<\\/timeLimitInMinutesOption>"
      )

    val noneTagPattern =
      Pattern.compile(
        "<timeLimitInMinutesOption class=\"none\".*\\/>"
      )
  }

}

/**
  * Imports problem bundles - according to version 1 of the document format
  */
class ProblemBundleImporter extends ProblemBundleImporterBase {
  override protected def readProblems(file: File, outputWriter: OutputWriter): Set[Problem] = {
    val updatedFileContent =
      replaceTimeLimitInMinutesTag(file)

    val sourceReader =
      new BufferedReader(
        new StringReader(
          updatedFileContent
        )
      )

    readProblemsInBundle(sourceReader)
  }


  private def replaceTimeLimitInMinutesTag(file: File): String = {
    val sourceBytes =
      Files.readAllBytes(file.toPath)

    val fileContent =
      new String(sourceBytes, Charset.forName("utf-8"))

    replaceTimeLimitInMinutesTag(fileContent)
  }


  @tailrec
  private def replaceTimeLimitInMinutesTag(cumulatedFileContent: String): String = {
    val someTagMatcher =
      ProblemBundleImporter.DurationInMinutesPatterns.someTagPattern.matcher(cumulatedFileContent)

    if (someTagMatcher.find()) {
      val timeLimitInMinutes =
        someTagMatcher.group(1).toInt

      val timeLimitInSeconds =
        timeLimitInMinutes * 60

      val replacementTag =
        "<timeLimitOption class=\"some\"><x class=\"java.time.Duration\">" + timeLimitInSeconds + "</x></timeLimitOption>"

      val updatedFileContent =
        someTagMatcher.replaceFirst(replacementTag)

      replaceTimeLimitInMinutesTag(
        updatedFileContent
      )
    } else {
      val noneTagMatcher =
        ProblemBundleImporter.DurationInMinutesPatterns.noneTagPattern.matcher(cumulatedFileContent)

      noneTagMatcher.replaceAll("<timeLimitOption class=\"none\"/>")
    }
  }

  override def canImport(file: File): Boolean =
    file.getName.toLowerCase.endsWith(FileExtensions.ProblemBundle)
}
