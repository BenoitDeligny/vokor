package games.vokor.gaming.domain.fighters

import games.vokor.gaming.domain.items.Item


@JvmInline
value class HealthPoints(val value: Int) {
    companion object {
        val Int.hp: HealthPoints get() = HealthPoints(this)
    }

    init {
        require(value >= 0) { "Health cannot be negative. Health = $value." }
    }

    fun noMoreHealthPoints(): Boolean = value == 0

    operator fun compareTo(other: Int): Int = this.value.compareTo(other)
    operator fun compareTo(other: Might): Int = this.value.compareTo(other.total)

    operator fun plus(other: Int): Int = value + other
    operator fun plus(other: Item): Int = value + other.enhancements.power.bonus

    operator fun minus(other: Int): Int = value - other
    operator fun minus(other: Item): Int = value - other.enhancements.power.bonus

}
