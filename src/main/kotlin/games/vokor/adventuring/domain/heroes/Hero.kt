package games.vokor.adventuring.domain.heroes

import games.vokor.adventuring.domain.dice.CombatDice
import games.vokor.adventuring.domain.dice.CombatDice.Companion.dice
import games.vokor.adventuring.domain.dice.Dice.d6Roll
import games.vokor.adventuring.domain.fight.Fighter
import games.vokor.adventuring.domain.fight.FightingPower
import games.vokor.adventuring.domain.fight.Initiative
import games.vokor.adventuring.domain.fight.Might
import games.vokor.adventuring.domain.storages.gear.Gear
import games.vokor.adventuring.domain.storages.gear.Gear.Companion.NONE
import games.vokor.adventuring.domain.heroes.CombatChart.combatDice
import games.vokor.adventuring.domain.storages.inventory.Inventory
import games.vokor.adventuring.domain.storages.inventory.Inventory.Companion.EMPTY
import games.vokor.adventuring.domain.items.Item

data class Hero(
    val identity: Identity,
    val abilities: Abilities,
    val inventory: Inventory = EMPTY,
    val gear: Gear = NONE,
    val fightingPower: FightingPower = FightingPower(
        might = Might(
            base = abilities.sum(),
            bonus = gear.power()
        )
    ),
) : Character, Fighter {

    override infix fun putOn(item: Item): Hero {
        val newGear = gear.add(item)

        return copy(
            gear = newGear,
            fightingPower = fightingPower.increaseBy(newGear)
        )
    }

    override infix fun putOff(item: Item): Hero {
        val newGear = gear.removes(item)

        return copy(
            gear = gear.removes(item),
            fightingPower = fightingPower.decreaseBy(newGear)
        )
    }

    override fun store(item: Item): Hero = copy(inventory = inventory.add(item))

    override fun takeOut(item: Item): Hero = copy(inventory = inventory.removes(item))

    override fun initiative(): Initiative = Initiative(
        value = abilities.agility + d6Roll(),
        agility = abilities.agility.value,
    )

    // TODO: should be private
    override fun combatDice(): CombatDice = combatDice(fightingPower.health).dice

    override fun attacks(): Int = combatDice().roll() + gear.damages()

    override fun damagedBy(damages: Int): Hero = copy(
        fightingPower = fightingPower.decreaseBy(damages - gear.damageReduction())
    )

    override fun healedBy(heal: Int): Hero = copy(
        fightingPower = fightingPower.increaseBy(heal)
    )

    override fun dead(): Boolean = fightingPower.health.noMoreHealthPoints()
}
