import bowling_game.BowlingGame
import bowling_game.IncompleteInfoForScore
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import org.junit.jupiter.api.Test

class BowlingKataTest {

    /**
     * [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1] => 20
     * [1,9,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0] => 12
     * [0,0,X,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0] => 14
     * [X,X,9,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0] => 61
     * [1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1] => 221
     * [10,10,10,10,10,10,10,10,10,10,10,10] => 300
     */

    @Test
    fun `should calculate the score for simple rolls`() {
        val bowlingGame = BowlingGame.withRollsOf(listOf(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1))

        bowlingGame.score() shouldBe 20
    }

    @Test
    fun `should calculate the score for strikes`() {
        val bowlingGame = BowlingGame.withRollsOf(listOf(0,0,10,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0))

        bowlingGame.score() shouldBe 14
    }

    @Test
    fun `should calculate the score for spares`() {
        val bowlingGame = BowlingGame.withRollsOf(listOf(1,9,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0))

        bowlingGame.score() shouldBe 13
    }

    @Test
    fun `should calculate the mix of spares and strikes`() {
        val bowlingGame = BowlingGame.withRollsOf(listOf(10,10,9,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0))

        bowlingGame.score() shouldBe 61
    }

    @Test
    fun `should calculate the score for all spares`() {
        val bowlingGame = BowlingGame.withRollsOf(listOf(1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1))

        bowlingGame.score() shouldBe 110

        val highest = BowlingGame.withRollsOf(listOf(9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,1,9,10))
        highest.score() shouldBe 190
    }

    @Test
    fun `should calculate the score for all strikes`() {
        val bowlingGame = BowlingGame.withRollsOf(listOf(10,10,10,10,10,10,10,10,10,10,10,10))

        bowlingGame.score() shouldBe 300
    }

    @Test
    fun `should catch an incomplete score`() {
        val bowlingGame = BowlingGame.withRollsOf(listOf(10,10))

        val exception = shouldThrow<IncompleteInfoForScore> { bowlingGame.score() }
        exception.message shouldBe "Need to roll again for displaying score"
    }
}