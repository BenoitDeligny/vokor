package games.vokor.adventuring.application.adventures.spi

import games.vokor.adventuring.domain.adventures.Adventure

interface AllAdventures {
    fun create(adventure: Adventure)
}