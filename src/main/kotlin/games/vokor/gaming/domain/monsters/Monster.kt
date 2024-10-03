package games.vokor.gaming.domain.monsters

import games.vokor.gaming.domain.dice.CombatDice
import games.vokor.gaming.domain.dice.CombatDice.Companion.dice
import games.vokor.gaming.domain.dice.Dice.d6Roll
import games.vokor.gaming.domain.fighters.Fighter
import games.vokor.gaming.domain.fighters.FightingPower
import games.vokor.gaming.domain.fighters.Initiative
import games.vokor.gaming.domain.storages.gear.Gear
import games.vokor.gaming.domain.monsters.CombatChart.combatDice

data class Monster(
    val identity: Identity,
    val gear: Gear,
    val fightingPower: FightingPower,
) : Fighter {

    override fun initiative(): Initiative = Initiative(
        value = (fightingPower.might / 3) + d6Roll(),
        agility = fightingPower.might / 3,
    )

    override fun attacks(): Int = combatDice().roll() + gear.damages()

    override fun damagedBy(damages: Int): Monster = copy(
        fightingPower = fightingPower.decreaseBy(damages - gear.damageReduction())
    )

    override fun healedBy(heal: Int): Monster = copy(
        fightingPower = fightingPower.increaseBy(heal)
    )

    override fun dead(): Boolean = fightingPower.health.noMoreHealthPoints()

    private fun combatDice(): CombatDice = combatDice(fightingPower.health).dice
}
