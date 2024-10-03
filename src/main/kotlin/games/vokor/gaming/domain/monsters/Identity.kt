package games.vokor.gaming.domain.monsters

import java.util.*
import java.util.UUID.randomUUID

data class Identity(
    val identifier: UUID = randomUUID(),
    val name: String,
) {
    init {
        require(name.all { it.isLetter() }) { "Name must contains only letters." }
    }
}
