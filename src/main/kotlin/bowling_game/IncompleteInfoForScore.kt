package bowling_game

class IncompleteInfoForScore : Throwable() {
    override val message: String
        get() = "Need to roll again for displaying score"
}
