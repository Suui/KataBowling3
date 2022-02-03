package bowling_game

interface Frame {
    fun score(): Int
}

class NormalFrame(private val firstRoll: Int, private val secondRoll: Int) : Frame {

    override fun score() = firstRoll + secondRoll
}


class SpareFrame(private val nextRoll: Int) : Frame {

    override fun score() = 10 + nextRoll
}

class StrikeFrame(private val nextRoll: Int, private val nextNextRoll: Int) : Frame {

    override fun score() = 10 + nextRoll + nextNextRoll
}