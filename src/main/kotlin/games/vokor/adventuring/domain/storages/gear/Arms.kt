package games.vokor.adventuring.domain.storages.gear

import games.vokor.adventuring.domain.storages.Storage
import games.vokor.adventuring.domain.items.Item
import games.vokor.adventuring.domain.items.Item.*

data class Arms(
    val bracers: Bracers = Bracers.emptySlot,
    val gloves: Gloves = Gloves.emptySlot,
    val rings: Rings = Rings(),
) : Bonuses, Storage {

    override fun add(item: Item): Arms = when (item) {
        is Bracers -> copy(bracers = item)
        is Gloves -> copy(gloves = item)
        is Ring -> copy(rings = rings.add(item))

        else -> this
    }

    override fun removes(item: Item): Arms = when (item) {
        is Bracers -> copy(bracers = Bracers.emptySlot)
        is Gloves -> copy(gloves = Gloves.emptySlot)
        is Ring -> copy(rings = rings.removes(item))

        else -> this
    }

    override fun power(): Int = bracers.enhancements.power.bonus +
            gloves.enhancements.power.bonus +
            rings.primary.enhancements.power.bonus +
            rings.secondary.enhancements.power.bonus

    override fun damages(): Int = bracers.enhancements.damages.bonus +
            gloves.enhancements.damages.bonus +
            rings.primary.enhancements.damages.bonus +
            rings.secondary.enhancements.damages.bonus

    override fun damageReduction(): Int = gloves.protection +
            gloves.enhancements.protection.bonus +
            bracers.enhancements.protection.bonus +
            rings.primary.enhancements.protection.bonus +
            rings.secondary.enhancements.protection.bonus
}

data class Rings(
    val primary: Ring = Ring.emptySlot,
    val secondary: Ring = Ring.emptySlot,
) {

    // This is V1
    fun add(ring: Ring): Rings = when {
        primary == Ring.emptySlot -> copy(primary = ring)
        secondary == Ring.emptySlot -> copy(primary = ring, secondary = primary)
        else -> copy(primary = ring, secondary = primary)
    }

    fun removes(ring: Ring): Rings = when {
        primary == ring -> copy(primary = Ring.emptySlot)
        secondary == ring -> copy(secondary = Ring.emptySlot)
        else -> this
    }
}
