package info.gianlucacosta.twobinmanager

import java.io.File

import info.gianlucacosta.twobinmanager.db.{DbConnection, DbProblemRepository, DbSolutionRepository}
import info.gianlucacosta.twobinpack.twobinmanager.ArtifactInfo


/**
  * Simplifies access to the application's database
  */
object AppDb {
  val DbFile: File =
    new File(
      App.Directory,
      "db"
    )

  val DbConnection: DbConnection = {
    val ddlSetupMode: String =
      if (dbExists)
        "none"
      else
        "create"

    new DbConnection(
      "javax.persistence.jdbc.url" -> s"jdbc:hsqldb:file:${DbFile}",
      "hibernate.show_sql" -> (!ArtifactInfo.release).toString,
      "format_sql" -> (!ArtifactInfo.release).toString,
      "hibernate.hbm2ddl.auto" -> ddlSetupMode
    )
  }

  lazy val problemRepository =
    new DbProblemRepository(DbConnection.entityManagerFactory)

  lazy val solutionRepository =
    new DbSolutionRepository(DbConnection.entityManagerFactory)


  private def dbExists: Boolean = {
    val dbTempDirectory =
      new File(
        DbFile.getAbsolutePath + ".tmp"
      )

    val dbLogFile =
      new File(
        DbFile.getAbsolutePath + ".log"
      )

    val dbScriptFile =
      new File(
        DbFile.getAbsolutePath + ".script"
      )

    val dbPropertiesFile =
      new File(
        DbFile.getAbsolutePath + ".properties"
      )

    dbTempDirectory.isDirectory || dbLogFile.isFile || dbScriptFile.isFile || dbPropertiesFile.isFile
  }
}
