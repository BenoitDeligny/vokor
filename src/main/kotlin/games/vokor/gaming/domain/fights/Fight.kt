package games.vokor.gaming.domain.fights

import games.vokor.gaming.domain.heroes.Hero
import games.vokor.gaming.domain.monsters.Monster

data class Fight(
    val hero: Hero,
    val monster: Monster
) {
}