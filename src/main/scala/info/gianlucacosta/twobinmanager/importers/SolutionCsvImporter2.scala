package info.gianlucacosta.twobinmanager.importers

import java.io.{BufferedReader, File}

import info.gianlucacosta.twobinpack.io.FileExtensions
import info.gianlucacosta.twobinpack.io.csv.v2.SolutionCsvReader2
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

/**
  * Imports one or more solutions from a dedicated CSV file - according to version 2 of the document format
  */
class SolutionCsvImporter2 extends SolutionCsvImporterBase[SolutionCsvReader2] {
  override protected def createSolutionsReader(sourceReader: BufferedReader, problemRepository: ProblemRepository): SolutionCsvReader2 =
    new SolutionCsvReader2(
      sourceReader,
      problemRepository
    )


  override protected def skipToNextSolution(solutionsReader: SolutionCsvReader2): Unit = {
    //Skip solver line
    solutionsReader.readLine()

    //Skip elapsed time line
    solutionsReader.readLine()

    skipAnchoredBlockLines(solutionsReader)
  }

  override def canImport(file: File): Boolean =
    file.getName.toLowerCase.endsWith(FileExtensions.CsvSolutionFile_v2)
}
