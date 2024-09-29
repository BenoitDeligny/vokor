package games.vokor.adventuring.domain.fight

import games.vokor.adventuring.domain.dice.CombatDice

interface Fighter {
    fun attacks(): Int
    fun combatDice(): CombatDice
    fun dead(): Boolean
    fun initiative(): Initiative
    infix fun damagedBy(damages: Int): Fighter
    infix fun healedBy(heal: Int): Fighter
}
