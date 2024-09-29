package games.vokor.adventuring.domain.items

import games.vokor.adventuring.domain.items.Enhancements.Companion.NONE
import java.util.*
import java.util.UUID.randomUUID

sealed class Item(
    val id: UUID = randomUUID(),
    open val enhancements: Enhancements = NONE,
) {

    data class Armor(
        override val enhancements: Enhancements = NONE,
        val protection: Int = 0,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Armor()
        }
    }

    data class Belt(
        override val enhancements: Enhancements = NONE,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Belt()
        }
    }

    data class Boots(
        override val enhancements: Enhancements = NONE,
        val protection: Int = 0,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Boots()
        }
    }

    data class Bracers(
        override val enhancements: Enhancements = NONE,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Bracers()
        }
    }

    data class Cloak(
        override val enhancements: Enhancements = NONE,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Cloak()
        }
    }

    data class Costume(
        override val enhancements: Enhancements = NONE,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Costume()
        }
    }

    data class Gloves(
        override val enhancements: Enhancements = NONE,
        val protection: Int = 0,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Gloves()
        }
    }

    data class Helmet(
        override val enhancements: Enhancements = NONE,
        val protection: Int = 0,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Helmet()
        }
    }

    data class Mask(
        override val enhancements: Enhancements = NONE,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Mask()
        }
    }

    data class Necklace(
        override val enhancements: Enhancements = NONE,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Necklace()
        }
    }

    data class Ring(
        override val enhancements: Enhancements = NONE,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Ring()
        }
    }

    data class Shield(
        override val enhancements: Enhancements = NONE,
        val protection: Int = 0,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Shield()
        }
    }

    data class Weapon(
        override val enhancements: Enhancements = NONE,
        val type: WeaponType,
    ) : Item(enhancements = enhancements) {
        companion object {
            val emptySlot = Weapon(type = WeaponType.ONE_HANDED)
        }

        enum class WeaponType {
            ONE_HANDED,
            TWO_HANDED,
        }
    }
}
