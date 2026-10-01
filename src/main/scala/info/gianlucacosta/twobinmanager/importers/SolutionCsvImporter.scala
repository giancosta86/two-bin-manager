package info.gianlucacosta.twobinmanager.importers

import java.io.{BufferedReader, File}

import info.gianlucacosta.twobinpack.io.FileExtensions
import info.gianlucacosta.twobinpack.io.csv.SolutionCsvReader
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

/**
  * Imports one or more solutions from a dedicated CSV file - according to version 1 of the document format
  */
class SolutionCsvImporter extends SolutionCsvImporterBase[SolutionCsvReader] {
  override protected def createSolutionsReader(sourceReader: BufferedReader, problemRepository: ProblemRepository): SolutionCsvReader =
    new SolutionCsvReader(
      sourceReader,
      problemRepository
    )


  override protected def skipToNextSolution(solutionsReader: SolutionCsvReader): Unit = {
    //Skip solver line
    solutionsReader.readLine()

    skipAnchoredBlockLines(solutionsReader)
  }


  override def canImport(file: File): Boolean =
    file.getName.toLowerCase.endsWith(FileExtensions.CsvSolutionFile)
}
