package games.vokor.gaming.domain.heroes

@JvmInline
value class Ability(val value: Int) {
    // TODO: remove it
    companion object {
        val Int.strength get() = Ability(this)
        val Int.agility get() = Ability(this)
        val Int.perception get() = Ability(this)
    }

    init {
        require(value > 0) { "An ability should be positive. Value = $value." }
    }

    operator fun plus(other: Ability): Ability = Ability(value + other.value)
    operator fun plus(other: Int): Int = value + other
}
