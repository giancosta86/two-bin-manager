package info.gianlucacosta.twobinmanager.db

import java.util.UUID
import javax.persistence.{EntityManagerFactory, NoResultException}

import info.gianlucacosta.helios.jpa.Includes._
import info.gianlucacosta.twobinmanager.db.DbConversions._
import info.gianlucacosta.twobinpack.core.Problem
import info.gianlucacosta.twobinpack.io.repositories.ProblemRepository

import scala.collection.JavaConversions._

/**
  * ProblemRepository backed by ORM on a database
  *
  * @param entityManagerFactory
  */
class DbProblemRepository(entityManagerFactory: EntityManagerFactory) extends ProblemRepository {
  override def findById(id: UUID): Option[Problem] = {
    entityManagerFactory.runUnitOfWork(entityManager => {
      val problemEntity =
        entityManager.find(classOf[ProblemEntity], id)

      Option(problemEntity)
    })
  }

  override def add(problem: Problem): Unit = {
    entityManagerFactory.runTransaction(entityManager => {
      entityManager.persist(problem: ProblemEntity)
    })
  }

  override def update(problem: Problem): Unit = {
    entityManagerFactory.runTransaction(entityManager => {
      entityManager.merge(problem: ProblemEntity)
    })
  }

  entityManagerFactory.addNamedQueryFor(
    getClass,
    "findAllNames",

    """
        SELECT problem.name
        FROM ProblemEntity problem
        ORDER BY problem.name
    """
  )

  override def findAllNamesSorted(): Iterable[String] = {
    entityManagerFactory.runUnitOfWork(entityManager => {
      entityManager
        .createNamedQueryFor(
          getClass,
          "findAllNames",
          classOf[String]
        )
        .getResultList
    })
  }


  entityManagerFactory.addNamedQueryFor(
    getClass,
    "findByName",

    """
      SELECT problem
      FROM ProblemEntity problem
      WHERE problem.name = :name
    """
  )

  def findByName(name: String): Option[Problem] = {
    entityManagerFactory.runUnitOfWork(entityManager => {
      val query =
        entityManager.createNamedQueryFor(
          getClass,
          "findByName",
          classOf[ProblemEntity]
        )
          .setParameter("name", name)

      try
        Some(query.getSingleResult)
      catch {
        case ex: NoResultException =>
          None
      }
    })
  }


  entityManagerFactory.addNamedQueryFor(
    getClass,
    "removeByName",

    """
      DELETE FROM ProblemEntity problem
      WHERE problem.name = :name
    """
  )

  override def removeByName(name: String): Unit = {
    entityManagerFactory.runTransaction(entityManager => {
      entityManager.createNamedQueryFor(
        getClass,
        "removeByName"
      )
        .setParameter("name", name)
        .executeUpdate()
    })
  }


  entityManagerFactory.addNamedQueryFor(
    getClass,
    "removeAll",

    """
      DELETE FROM ProblemEntity problem
    """
  )

  override def removeAll(): Unit =
    entityManagerFactory.runTransaction(entityManager => {
      entityManager.createNamedQueryFor(
        getClass,
        "removeAll"
      )
        .executeUpdate()
    })


  entityManagerFactory.addNamedQueryFor(
    getClass,
    "count",

    """
      SELECT COUNT(id)
      FROM ProblemEntity
    """
  )

  override def count(): Long =
    entityManagerFactory.runUnitOfWork(entityManager => {
      entityManager
        .createNamedQueryFor(
          getClass,
          "count",
          classOf[java.lang.Long]
        )
        .getSingleResult
    })
}
