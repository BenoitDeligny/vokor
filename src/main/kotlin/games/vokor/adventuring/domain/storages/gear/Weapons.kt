package games.vokor.adventuring.domain.storages.gear

import games.vokor.adventuring.domain.storages.Storage
import games.vokor.adventuring.domain.items.Item
import games.vokor.adventuring.domain.items.Item.Shield
import games.vokor.adventuring.domain.items.Item.Weapon
import games.vokor.adventuring.domain.items.Item.Weapon.WeaponType.ONE_HANDED
import games.vokor.adventuring.domain.items.Item.Weapon.WeaponType.TWO_HANDED

data class Weapons(
    val mainHand: Weapon = Weapon.emptySlot,
    val offHand: Shield = Shield.emptySlot,
) : Bonuses, Storage {

    override fun add(item: Item): Weapons = when (item) {
        is Weapon -> when (item.type) {
            ONE_HANDED -> copy(mainHand = item)
            TWO_HANDED -> copy(mainHand = item, offHand = Shield.emptySlot)
        }

        is Shield -> when {
            (mainHand.type == TWO_HANDED) -> copy(mainHand = Weapon.emptySlot, offHand = item)
            else -> copy(offHand = item)
        }

        else -> this
    }

    override fun removes(item: Item): Weapons = when (item) {
        is Weapon -> copy(mainHand = Weapon.emptySlot)
        is Shield -> copy(offHand = Shield.emptySlot)

        else -> this
    }

    override fun power(): Int = mainHand.enhancements.power.bonus +
            offHand.enhancements.power.bonus

    override fun damages(): Int = mainHand.enhancements.damages.bonus +
            offHand.enhancements.damages.bonus

    override fun damageReduction(): Int = offHand.protection +
            offHand.enhancements.protection.bonus +
            mainHand.enhancements.protection.bonus
}
