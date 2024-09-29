package games.vokor.adventuring.domain.heroes

data class Abilities(
    val strength: Ability,
    val agility: Ability,
    val perception: Ability,
) {
    fun sum() = (strength + agility + perception).value
}

