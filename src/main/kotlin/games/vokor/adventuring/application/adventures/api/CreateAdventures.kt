package games.vokor.adventuring.application.adventures.api

import games.vokor.adventuring.domain.adventures.Adventure
import games.vokor.adventuring.domain.adventures.Difficulty
import games.vokor.adventuring.domain.heroes.Hero
import java.lang.Exception

interface CreateAdventures {
    fun create(difficulty: Difficulty, hero: Hero): CreateAdventureResult
}

sealed class CreateAdventureResult {
    data class Success(val adventure: Adventure) : CreateAdventureResult()
    data class Failure(val message: String, val exception: Exception) : CreateAdventureResult()
}