package bowling_game

import java.lang.IndexOutOfBoundsException

class BowlingGame(private val rolls: MutableList<Int> = mutableListOf(), private val frames: MutableList<Frame> = mutableListOf()) {

    fun roll(pins: Int) {
        rolls.add(pins)
    }

    fun score(): Int {
        parseRollsFrom(rolls)
        return frames.sumOf { frame -> frame.score() }
    }

    private fun parseRollsFrom(rolls: MutableList<Int>) {
        if (rolls.size == 0 || frames.size == 10) return

        try {
            val currentRoll = rolls[0]

            if (currentRoll == 10) {
                frames.add(StrikeFrame(rolls[1], rolls[2]))
                return parseRollsFrom(rolls.subList(1, rolls.size))
            }

            val nextRoll = rolls[1]
            if (currentRoll + nextRoll == 10) {
                frames.add(SpareFrame(rolls[2]))
                return parseRollsFrom(rolls.subList(2, rolls.size))
            }

            frames.add(NormalFrame(currentRoll, nextRoll))
            return parseRollsFrom(rolls.subList(2, rolls.size))
        } catch (_: IndexOutOfBoundsException) {
            throw IncompleteInfoForScore()
        }
    }

    companion object {

        fun withRollsOf(rolls: List<Int>): BowlingGame {
            val game = BowlingGame()

            rolls.forEach { pins -> game.roll(pins) }

            return game
        }
    }

}


