package games.vokor.gaming.domain.heroes

import games.vokor.gaming.domain.storages.gear.Chest
import games.vokor.gaming.domain.storages.gear.Gear
import games.vokor.gaming.domain.storages.gear.Weapons
import games.vokor.gaming.domain.heroes.AbilitiesFactory.anAbilities
import games.vokor.gaming.domain.heroes.AbilitiesFactory.easyModeAbilities
import games.vokor.gaming.domain.heroes.AbilitiesFactory.normalModeAbilities
import games.vokor.gaming.domain.heroes.AbilitiesFactory.withAgility
import games.vokor.gaming.domain.heroes.AbilitiesFactory.withPerception
import games.vokor.gaming.domain.heroes.AbilitiesFactory.withStrength
import games.vokor.gaming.domain.fighters.HealthPoints.Companion.hp
import games.vokor.gaming.domain.fighters.Might
import games.vokor.gaming.domain.heroes.Identity.Age.Companion.years
import games.vokor.gaming.domain.heroes.IdentityFactory.aHeroIdentity
import games.vokor.gaming.domain.heroes.IdentityFactory.withName
import games.vokor.gaming.domain.items.Enhancements
import games.vokor.gaming.domain.items.Enhancements.Damage
import games.vokor.gaming.domain.items.Enhancements.Power
import games.vokor.gaming.domain.items.Item
import games.vokor.gaming.domain.items.Item.Armor
import games.vokor.gaming.domain.items.Item.Weapon
import games.vokor.gaming.domain.items.Item.Weapon.WeaponType.ONE_HANDED

object HeroesFactory {

    fun aHero(): Hero = Hero(
        identity = Identity(name = "MyHero", age = 15.years),
        abilities = anAbilities()
    )

    fun Hero.withName(name: String): Hero = copy(
        identity = aHeroIdentity().withName(name)
    )

    fun Hero.withAge(age: Int): Hero = copy(
        identity = identity.copy(age = age.years)
    )

    fun Hero.withStrength(strength: Int): Hero = copy(
        abilities = abilities.withStrength(strength)
    )

    fun Hero.withAgility(agility: Int): Hero = copy(
        abilities = abilities.withAgility(agility)
    )

    fun Hero.withPerception(perception: Int): Hero = copy(
        abilities = abilities.withPerception(perception)
    )

    fun Hero.withItem(item: Item): Hero = putOn(item)

    fun Hero.withInventory(item: Item): Hero = store(item)

    fun Hero.withPowerThreshold(threshold: Int): Hero = copy(
        fightingPower = fightingPower.copy(might = Might(base = threshold))
    )

    fun Hero.withHealthPoints(health: Int): Hero = copy(
        fightingPower = fightingPower.copy(health = health.hp)
    )

    fun aRandomHeroInNormalMode(name: String): Hero = Hero(
        identity = aHeroIdentity().withName(name),
        abilities = normalModeAbilities(),
        gear = Gear(
            chest = Chest(
                armor = Armor(
                    protection = 0,
                    enhancements = Enhancements(power = Power(1), damages = Damage.ZERO)
                )
            ),
            weapons = Weapons(
                mainHand = Weapon(
                    type = ONE_HANDED,
                    enhancements = Enhancements(power = Power.ZERO, damages = Damage(1))
                ),
            )
        ),
    )

    fun aRandomHeroInEasyMode(name: String): Hero = Hero(
        identity = aHeroIdentity().withName(name),
        abilities = easyModeAbilities(),
        gear = Gear(
            chest = Chest(
                armor = Armor(
                    protection = 0,
                    enhancements = Enhancements(power = Power(3), damages = Damage.ZERO)
                )
            ),
            weapons = Weapons(
                mainHand = Weapon(
                    type = ONE_HANDED,
                    enhancements = Enhancements(power = Power.ZERO, damages = Damage(3))
                ),
            )
        ),
    )
}
