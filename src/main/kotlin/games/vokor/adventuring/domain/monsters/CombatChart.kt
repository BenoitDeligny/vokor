package games.vokor.adventuring.domain.monsters

import games.vokor.adventuring.domain.fight.HealthPoints

data object CombatChart {
    private val levels = mapOf(
        0 to 1..8,
        1 to 9..24,
        2 to 25..40,
        3 to 41..64,
        4 to 65..88,
        5 to 89..124,
        6 to 125..172,
        7 to 173..220,
        8 to 221..276,
        9 to 277..332,
        10 to 333..Int.MAX_VALUE
    )

    fun combatDice(health: HealthPoints): Int = levels
        .entries
        .find { entry -> health.value in entry.value }
        ?.key ?: 0
}
