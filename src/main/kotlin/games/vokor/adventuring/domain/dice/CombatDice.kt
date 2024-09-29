package games.vokor.adventuring.domain.dice

import games.vokor.adventuring.domain.dice.Dice.d6Roll

@JvmInline
value class CombatDice(private val dice: List<Dice>) {
    companion object {
        val Int.dice: CombatDice get() = CombatDice(List(this) { Dice })
    }

    fun roll() = dice.sumOf { d6Roll() }
}
