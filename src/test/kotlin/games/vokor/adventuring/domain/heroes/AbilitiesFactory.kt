package games.vokor.adventuring.domain.heroes

import games.vokor.adventuring.domain.heroes.Ability.Companion.agility
import games.vokor.adventuring.domain.heroes.Ability.Companion.perception
import games.vokor.adventuring.domain.heroes.Ability.Companion.strength
import games.vokor.adventuring.domain.adventures.Difficulty
import games.vokor.adventuring.domain.adventures.Difficulty.EASY
import games.vokor.adventuring.domain.adventures.Difficulty.NORMAL
import games.vokor.adventuring.domain.dice.Dice.d6Roll

object AbilitiesFactory {

    fun anAbilities() = Abilities(
        strength = 5.strength,
        agility = 5.agility,
        perception = 5.perception
    )

    fun Abilities.withStrength(value: Int) = this.copy(strength = value.strength)
    fun Abilities.withAgility(value: Int) = this.copy(agility = value.agility)
    fun Abilities.withPerception(value: Int) = this.copy(perception = value.perception)

    fun normalModeAbilities() = Abilities(
        strength = randomIn(NORMAL),
        agility = randomIn(NORMAL),
        perception = randomIn(NORMAL)
    )

    fun easyModeAbilities() = Abilities(
        strength = randomIn(EASY),
        agility = randomIn(EASY),
        perception = randomIn(EASY)
    )


    private fun randomIn(mode: Difficulty) = Ability(d6Roll() + mode.abilityModifier)
}
