package games.vokor.gaming.domain.storages.gear

import games.vokor.gaming.domain.items.Item
import games.vokor.gaming.domain.items.Item.*
import games.vokor.gaming.domain.storages.Storage

data class Gear(
    val head: Head = Head(),
    val chest: Chest = Chest(),
    val arms: Arms = Arms(),
    val legs: Legs = Legs(),
    val weapons: Weapons = Weapons(),
) : Bonuses, Storage {
    companion object {
        val NONE = Gear()
    }

    override fun power(): Int = head.power() +
            chest.power() +
            arms.power() +
            legs.power() +
            weapons.power()

    override fun damages(): Int = head.damages() +
            chest.damages() +
            arms.damages() +
            legs.damages() +
            weapons.damages()

    override fun damageReduction(): Int = head.damageReduction() +
            chest.damageReduction() +
            arms.damageReduction() +
            legs.damageReduction() +
            weapons.damageReduction()

    override fun add(item: Item): Gear = when (item) {
        is Helmet,
        is Mask,
        is Necklace,
            -> copy(head = head.add(item))

        is Bracers,
        is Gloves,
        is Ring,
            -> copy(arms = arms.add(item))

        is Armor,
        is Cloak,
        is Costume,
            -> copy(chest = chest.add(item))

        is Boots,
        is Belt,
            -> copy(legs = legs.add(item))

        is Shield,
        is Weapon,
            -> copy(weapons = weapons.add(item))
    }

    override fun removes(item: Item): Gear = when (item) {
        is Helmet,
        is Mask,
        is Necklace,
            -> copy(head = head.removes(item))

        is Bracers,
        is Gloves,
        is Ring,
            -> copy(arms = arms.removes(item))

        is Armor,
        is Cloak,
        is Costume,
            -> copy(chest = chest.removes(item))

        is Boots,
        is Belt,
            -> copy(legs = legs.removes(item))

        is Shield,
        is Weapon,
            -> copy(weapons = weapons.removes(item))
    }
}

