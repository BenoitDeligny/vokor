package games.vokor.adventuring.application.adventures

import games.vokor.adventuring.application.adventures.api.CreateAdventureResult
import games.vokor.adventuring.application.adventures.api.CreateAdventureResult.*
import games.vokor.adventuring.application.adventures.api.CreateAdventures
import games.vokor.adventuring.application.adventures.spi.AllAdventures
import games.vokor.adventuring.domain.adventures.Adventure
import games.vokor.adventuring.domain.adventures.Difficulty
import games.vokor.adventuring.domain.heroes.Hero

class CreateAdventures(
    private val adventures: AllAdventures
) : CreateAdventures {
    override fun create(difficulty: Difficulty, hero: Hero): CreateAdventureResult =
        with(Adventure(difficulty = difficulty, hero = hero)) {
            return try {
                adventures.create(this)
                Success(this)
            } catch (e: Exception) {
                Failure("Fails to create adventure", e)
            }
        }
}