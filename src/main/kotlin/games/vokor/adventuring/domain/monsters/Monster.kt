package games.vokor.adventuring.domain.monsters

import games.vokor.adventuring.domain.dice.CombatDice
import games.vokor.adventuring.domain.dice.CombatDice.Companion.dice
import games.vokor.adventuring.domain.dice.Dice.d6Roll
import games.vokor.adventuring.domain.fight.Fighter
import games.vokor.adventuring.domain.fight.FightingPower
import games.vokor.adventuring.domain.fight.Initiative
import games.vokor.adventuring.domain.storages.gear.Gear
import games.vokor.adventuring.domain.monsters.CombatChart.combatDice

data class Monster(
    val identity: Identity,
    val gear: Gear,
    val fightingPower: FightingPower,
) : Fighter {

    override fun initiative(): Initiative = Initiative(
        value = (fightingPower.might / 3) + d6Roll(),
        agility = fightingPower.might / 3,
    )

    override fun combatDice(): CombatDice = combatDice(fightingPower.health).dice

    override fun attacks(): Int = combatDice().roll() + gear.damages()

    override fun damagedBy(damages: Int): Monster = copy(
        fightingPower = fightingPower.decreaseBy(damages - gear.damageReduction())
    )

    override fun healedBy(heal: Int): Monster = copy(
        fightingPower = fightingPower.increaseBy(heal)
    )

    override fun dead(): Boolean = fightingPower.health.noMoreHealthPoints()
}
