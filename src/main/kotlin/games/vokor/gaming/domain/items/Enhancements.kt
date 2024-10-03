package games.vokor.gaming.domain.items

data class Enhancements(
    val power: Power = Power.ZERO,
    val damages: Damage = Damage.ZERO,
    val protection: Protection = Protection.ZERO,
) {
    companion object {
        val NONE = Enhancements()
    }

    operator fun plus(other: Enhancements): Enhancements = Enhancements(
        power = power + other.power.bonus,
        damages = damages + other.damages.bonus,
        protection = protection + other.protection.bonus,
    )

    @JvmInline
    value class Power(val bonus: Int) {

        companion object {
            val ZERO = Power(0)
            val Int.power: Power get() = Power(this)
        }

        operator fun plus(other: Int): Power = Power(bonus + other)
    }

    @JvmInline
    value class Damage(val bonus: Int) {

        companion object {
            val ZERO = Damage(0)
            val Int.damages: Damage get() = Damage(this)
        }

        operator fun plus(other: Int): Damage = Damage(bonus + other)
    }

    @JvmInline
    value class Protection(val bonus: Int) {

        companion object {
            val ZERO = Protection(0)
            val Int.protection: Protection get() = Protection(this)
        }

        operator fun plus(other: Int): Protection = Protection(bonus + other)
    }
}
