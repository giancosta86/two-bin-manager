package info.gianlucacosta.twobinmanager.importers

import java.io.{File, StringWriter}

import info.gianlucacosta.twobinmanager.sdk.importers.Importer
import info.gianlucacosta.twobinmanager.sdk.io.OutputWriter
import info.gianlucacosta.twobinmanager.{App, AppDb}

/**
  * Imports one or more files, using the available Importer instances
  */
object ImportService {
  /**
    * Imports the given files - after sorting them by name - and returns
    * the output string created by the dedicated logger.
    *
    * @param files The files to import
    * @return The log string
    */
  def importFiles(files: Iterable[File]): String = {
    val stringWriter =
      new StringWriter

    val outputWriter =
      new OutputWriter(stringWriter)


    files
      .toList
      .sortBy(_.getName)
      .foreach(file => {
        outputWriter.printHeader(s"Importing file '${file.getAbsolutePath}'")

        val importerOption: Option[Importer] =
          App.PluginsRegistry.importers.find(_.canImport(file))

        importerOption match {
          case Some(importer) =>
            outputWriter.println(s"Applying importer: '${importer.getClass.getCanonicalName}'")
            outputWriter.println()

            importer.importFile(
              file,
              outputWriter,
              AppDb.problemRepository,
              AppDb.solutionRepository
            )

          case None =>
            outputWriter.printOutcome("No suitable importer found")
        }
      })

    stringWriter.toString
  }
}
