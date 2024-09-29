package games.vokor.adventuring.domain.heroes

import games.vokor.adventuring.domain.dice.Dice.d6Roll

object IdentityFactory {

    fun aHeroIdentity() = Identity(
        name = "NewIdentity",
        age = random()
    )

    fun Identity.withName(name: String) = copy(name = name)

    private fun random() = Identity.Age(14 + d6Roll())
}
