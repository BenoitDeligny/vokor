package games.vokor.adventuring.domain.storages.gear

import games.vokor.adventuring.domain.storages.Storage
import games.vokor.adventuring.domain.items.Item
import games.vokor.adventuring.domain.items.Item.Belt
import games.vokor.adventuring.domain.items.Item.Boots

data class Legs(
    val belt: Belt = Belt.emptySlot,
    val boots: Boots = Boots.emptySlot,
) : Bonuses, Storage {

    override fun add(item: Item): Legs = when (item) {
        is Belt -> copy(belt = item)
        is Boots -> copy(boots = item)

        else -> this
    }

    override fun removes(item: Item): Legs = when (item) {
        is Belt -> copy(belt = Belt.emptySlot)
        is Boots -> copy(boots = Boots.emptySlot)

        else -> this
    }

    override fun power(): Int = boots.enhancements.power.bonus +
            belt.enhancements.power.bonus

    override fun damages(): Int = boots.enhancements.damages.bonus +
            belt.enhancements.damages.bonus

    override fun damageReduction(): Int = boots.protection +
            boots.enhancements.protection.bonus +
            belt.enhancements.protection.bonus
}
