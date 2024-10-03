package games.vokor.gaming.domain.fighters

interface Fighter {
    fun attacks(): Int
    fun dead(): Boolean
    fun initiative(): Initiative
    infix fun damagedBy(damages: Int): Fighter
    infix fun healedBy(heal: Int): Fighter
}
