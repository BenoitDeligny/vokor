package games.vokor.gaming.domain.storages.gear

import games.vokor.gaming.domain.storages.Storage
import games.vokor.gaming.domain.items.Item
import games.vokor.gaming.domain.items.Item.*

data class Chest(
    val armor: Armor = Armor.emptySlot,
    val cloak: Cloak = Cloak.emptySlot,
    val costume: Costume = Costume.emptySlot,
) : Bonuses, Storage {
    override fun add(item: Item): Chest = when (item) {
        is Armor -> copy(armor = item)
        is Cloak -> copy(cloak = item)
        is Costume -> copy(costume = item)

        else -> this
    }

    override fun removes(item: Item): Chest = when (item) {
        is Armor -> copy(armor = Armor.emptySlot)
        is Cloak -> copy(cloak = Cloak.emptySlot)
        is Costume -> copy(costume = Costume.emptySlot)

        else -> this
    }

    override fun power(): Int = armor.enhancements.power.bonus +
            cloak.enhancements.power.bonus +
            costume.enhancements.power.bonus

    override fun damages(): Int = armor.enhancements.damages.bonus +
            cloak.enhancements.damages.bonus +
            costume.enhancements.damages.bonus

    override fun damageReduction(): Int = armor.protection +
            armor.enhancements.protection.bonus +
            cloak.enhancements.protection.bonus +
            costume.enhancements.protection.bonus
}
