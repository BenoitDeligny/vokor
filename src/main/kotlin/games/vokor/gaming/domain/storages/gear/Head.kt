package games.vokor.gaming.domain.storages.gear

import games.vokor.gaming.domain.storages.Storage
import games.vokor.gaming.domain.items.Item
import games.vokor.gaming.domain.items.Item.*

data class Head(
    val helmet: Helmet = Helmet.emptySlot,
    val mask: Mask = Mask.emptySlot,
    val necklace: Necklace = Necklace.emptySlot,
) : Bonuses, Storage {

    override fun add(item: Item): Head = when (item) {
        is Helmet -> copy(helmet = item)
        is Mask -> copy(mask = item)
        is Necklace -> copy(necklace = item)

        else -> this
    }

    override fun removes(item: Item): Head = when (item) {
        is Helmet -> copy(helmet = Helmet.emptySlot)
        is Mask -> copy(mask = Mask.emptySlot)
        is Necklace -> copy(necklace = Necklace.emptySlot)

        else -> this
    }

    override fun power(): Int = helmet.enhancements.power.bonus +
            mask.enhancements.power.bonus +
            necklace.enhancements.power.bonus

    override fun damages(): Int = helmet.enhancements.damages.bonus +
            mask.enhancements.damages.bonus +
            necklace.enhancements.damages.bonus

    override fun damageReduction(): Int = helmet.protection +
            helmet.enhancements.protection.bonus +
            mask.enhancements.protection.bonus +
            necklace.enhancements.protection.bonus
}
