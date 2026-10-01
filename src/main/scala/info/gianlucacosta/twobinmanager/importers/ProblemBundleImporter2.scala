package info.gianlucacosta.twobinmanager.importers

import java.io.File

import info.gianlucacosta.twobinpack.io.FileExtensions

/**
  * Imports problem bundles - according to version 2 of the document format
  */
class ProblemBundleImporter2 extends ProblemBundleImporterBase {
  override def canImport(file: File): Boolean =
    file.getName.toLowerCase.endsWith(FileExtensions.ProblemBundle_v2)
}
