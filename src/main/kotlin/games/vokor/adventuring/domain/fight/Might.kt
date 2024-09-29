package games.vokor.adventuring.domain.fight

import games.vokor.adventuring.domain.items.Item

data class Might(
    val base: Int,
    val bonus: Int = 0,
) {
    val total = base + bonus

    init {
        require(base >= 0) { "Base of threshold cannot be negative. Base = $base." }
        require(total >= 0) { "Threshold cannot be negative. Threshold = $total." }
    }

    operator fun compareTo(other: Int): Int = total.compareTo(other)
    operator fun plus(other: Item): Int = total + other.enhancements.power.bonus
    operator fun minus(other: Item): Int = total - other.enhancements.power.bonus
    operator fun div(other: Int): Int = total / other
}
