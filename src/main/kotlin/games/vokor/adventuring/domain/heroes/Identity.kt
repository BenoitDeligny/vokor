package games.vokor.adventuring.domain.heroes

import java.util.*
import java.util.UUID.randomUUID

data class Identity(
    val identifier: UUID = randomUUID(),
    val name: String,
    val age: Age,
) {
    init {
        require(name.all { it.isLetter() }) { "Name must contains only letters." }
        require(age in 15..20) { "Age must be between 15 and 20. Age = $age." }
    }

    operator fun IntRange.contains(age: Age): Boolean {
        return age.value in this
    }

    @JvmInline
    value class Age(val value: Int) {
        companion object {
            val Int.years: Age get() = Age(this)
        }
    }
}
