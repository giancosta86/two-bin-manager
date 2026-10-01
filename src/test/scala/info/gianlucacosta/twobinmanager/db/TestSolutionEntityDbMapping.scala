package info.gianlucacosta.twobinmanager.db

import info.gianlucacosta.twobinmanager.db.DbConversions._
import info.gianlucacosta.twobinpack.test.SimpleTestData


class TestSolutionEntityDbMapping extends DbTestBase {

  "SolutionEntity" should "be persisted correctly" in {
    val testProblemEntity: ProblemEntity =
      SimpleTestData.ProblemA

    val testSolutionEntity: SolutionEntity =
      SimpleTestData.SolutionA1

    runTransaction {
      entityManager.persist(testProblemEntity)
    }

    runTransaction {
      entityManager.persist(testSolutionEntity)
    }
  }


  "SolutionEntity" should "NOT be persisted correctly without the related problem" in {
    val testSolutionEntity: SolutionEntity =
      SimpleTestData.SolutionA1

    intercept[Exception] {
      runTransaction {
        entityManager.persist(testSolutionEntity)
      }
    }
  }


  "SolutionEntity" should "be retrieved correctly" in {
    val testProblemEntity: ProblemEntity =
      SimpleTestData.ProblemA

    val testSolutionEntity: SolutionEntity =
      SimpleTestData.SolutionA1


    runTransaction {
      entityManager.persist(testProblemEntity)
    }


    runTransaction {
      entityManager.persist(testSolutionEntity)
    }

    val retrievedSolutionEntity =
      entityManager.find(classOf[SolutionEntity], testSolutionEntity.id)

    retrievedSolutionEntity should be(testSolutionEntity)
  }
}
