package info.gianlucacosta.twobinmanager.db

import info.gianlucacosta.twobinmanager.db.DbConversions._
import info.gianlucacosta.twobinpack.test.SimpleTestData


class TestProblemEntityDbMapping extends DbTestBase {
  "ProblemEntity" should "be persisted correctly" in {
    val problemEntity: ProblemEntity =
      SimpleTestData.ProblemA

    runTransaction {
      entityManager.persist(problemEntity)
    }
  }


  "ProblemEntity" should "be retrieved correctly" in {
    val problemEntity: ProblemEntity =
      SimpleTestData.ProblemA

    runTransaction {
      entityManager.persist(problemEntity)
    }

    val retrievedEntity =
      entityManager.find(classOf[ProblemEntity], problemEntity.id)


    retrievedEntity should be(problemEntity)
  }
}
