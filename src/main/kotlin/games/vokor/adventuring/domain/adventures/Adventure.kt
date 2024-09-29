package games.vokor.adventuring.domain.adventures

import games.vokor.adventuring.domain.heroes.Hero
import java.util.UUID
import java.util.UUID.randomUUID

data class Adventure(
    val id: UUID = randomUUID(),
    val difficulty: Difficulty,
    val hero: Hero,
)