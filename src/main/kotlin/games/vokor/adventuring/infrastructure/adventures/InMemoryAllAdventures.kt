package games.vokor.adventuring.infrastructure.adventures

import games.vokor.adventuring.application.adventures.spi.AllAdventures
import games.vokor.adventuring.domain.adventures.Adventure
import org.springframework.stereotype.Repository

@Repository
class InMemoryAllAdventures(private val adventures: MutableList<Adventure> = mutableListOf()) : AllAdventures {
    override fun create(adventure: Adventure) {
        adventures.add(adventure)
    }

    fun clear() {
        adventures.clear()
    }

    fun all(): List<Adventure> {
        return adventures
    }
}