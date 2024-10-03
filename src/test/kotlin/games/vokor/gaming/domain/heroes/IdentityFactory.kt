package games.vokor.gaming.domain.heroes

import games.vokor.gaming.domain.dice.Dice.d6Roll

object IdentityFactory {

    fun aHeroIdentity() = Identity(
        name = "NewIdentity",
        age = random()
    )

    fun Identity.withName(name: String) = copy(name = name)

    private fun random() = Identity.Age(14 + d6Roll())
}
