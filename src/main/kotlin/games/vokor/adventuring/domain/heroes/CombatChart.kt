package games.vokor.adventuring.domain.heroes

import games.vokor.adventuring.domain.fight.HealthPoints

data object CombatChart {
    private val levels = mapOf(
        0 to 1..5,
        1 to 6..18,
        2 to 19..30,
        3 to 31..54,
        4 to 55..78,
        5 to 79..114,
        6 to 115..150,
        7 to 151..192,
        8 to 193..234,
        9 to 235..282,
        10 to 283..Int.MAX_VALUE
    )

    fun combatDice(health: HealthPoints): Int = levels
        .entries
        .find { entry -> health.value in entry.value }
        ?.key ?: 0
}
