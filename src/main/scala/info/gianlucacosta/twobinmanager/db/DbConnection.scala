package info.gianlucacosta.twobinmanager.db

import javax.persistence.{EntityManagerFactory, Persistence}

import scala.collection.JavaConversions._

/**
  * JPA-based database connection providing architectural ORM mappings and
  * initialized using the given parameters.
  *
  * It should be opened via the <i>open()</i> method and closed via
  * the <i>close()</i> method.
  *
  * Furthermore, it provides an EntityManager object that can be used to
  * read objects from the db.
  *
  * @param entityManagerFactoryParams Initialization parameters for the EntityManagerFactory
  */
class DbConnection(entityManagerFactoryParams: (String, String)*) {
  private var _entityManagerFactory: EntityManagerFactory = _

  /**
    * The EntityManagerFactory created when starting the db connection
    *
    * @return The EntityManagerFactory, or null if the connection is closed
    */
  def entityManagerFactory: EntityManagerFactory =
    _entityManagerFactory


  /**
    * Opens the db connection
    */
  def open(): Unit = {
    require(_entityManagerFactory == null)

    _entityManagerFactory =
      Persistence.createEntityManagerFactory(
        "info.gianlucacosta.twobinpack.jpa",

        entityManagerFactoryParams.toMap[String, String]
      )
  }


  /**
    * Closes the db connection
    */
  def close(): Unit = {
    require(_entityManagerFactory != null)

    _entityManagerFactory.close()

    _entityManagerFactory =
      null
  }
}
