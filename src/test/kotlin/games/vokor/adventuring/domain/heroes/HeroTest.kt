package games.vokor.adventuring.domain.heroes

import games.vokor.adventuring.domain.fight.FightingPower
import games.vokor.adventuring.domain.heroes.Ability.Companion.agility
import games.vokor.adventuring.domain.heroes.Ability.Companion.perception
import games.vokor.adventuring.domain.heroes.Ability.Companion.strength
import games.vokor.adventuring.domain.fight.HealthPoints.Companion.hp
import games.vokor.adventuring.domain.fight.Might
import games.vokor.adventuring.domain.heroes.HeroesFactory.aHero
import games.vokor.adventuring.domain.heroes.HeroesFactory.aRandomHeroInNormalMode
import games.vokor.adventuring.domain.heroes.HeroesFactory.withHealthPoints
import games.vokor.adventuring.domain.heroes.HeroesFactory.withInventory
import games.vokor.adventuring.domain.heroes.HeroesFactory.withItem
import games.vokor.adventuring.domain.heroes.IdentityFactory.aHeroIdentity
import games.vokor.adventuring.domain.items.Enhancements
import games.vokor.adventuring.domain.items.Enhancements.Power.Companion.power
import games.vokor.adventuring.domain.items.Enhancements.Protection.Companion.protection
import games.vokor.adventuring.domain.items.Item.*
import games.vokor.adventuring.domain.items.Item.Weapon.WeaponType.ONE_HANDED
import games.vokor.adventuring.domain.items.Item.Weapon.WeaponType.TWO_HANDED
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class HeroTest {

    @Nested
    inner class HeroTakesDamaged {
        @Test
        fun `hero takes damages and lives`() {
            // given
            // when
            val hero = aHero().run { this damagedBy 5 }

            // then
            hero.fightingPower.might shouldBe Might(base = 15)
            hero.fightingPower.health shouldBe 10.hp
            hero.dead() shouldBe false
        }

        @Test
        fun `hero takes damages and dies`() {
            // given
            // when
            val hero = aHero().run { this damagedBy 15 }

            // then
            hero.fightingPower.might shouldBe Might(base = 15)
            hero.fightingPower.health shouldBe 0.hp
            hero.dead() shouldBe true
        }

        @Test
        fun `hero can't be take more damages than remaining might`() {
            // given
            // when
            val hero = aHero().run { this damagedBy 25 }

            // then
            hero.fightingPower.might shouldBe Might(base = 15)
            hero.fightingPower.health shouldBe 0.hp
        }

        @Test
        fun `hero take less damages due to protection of armor`() {
            // given
            // when
            val hero = aHero()
                .withHealthPoints(15)
                .withItem(Armor(protection = 5))
                .run { this damagedBy 10 }

            // then
            hero.fightingPower.health shouldBe 10.hp
        }

        @Test
        fun `hero take less damages due to protection of magical bracers`() {
            // given
            // when
            val hero = aHero()
                .withHealthPoints(15)
                .withItem(Bracers(enhancements = Enhancements(protection = 5.protection)))
                .run { this damagedBy 10 }

            // then
            hero.fightingPower.health shouldBe 10.hp
        }

        @Test
        fun `hero take less damages due to protection of armor and shield`() {
            // given
            // when
            val hero = aHero()
                .withHealthPoints(15)
                .withItem(Armor(protection = 5))
                .withItem(Shield(protection = 5))
                .run { this damagedBy 15 }

            // then
            hero.fightingPower.health shouldBe 10.hp
        }

        @Test
        fun `hero lose one health point even the protection is over damages`() {
            // given
            // when
            val hero = aHero()
                .withHealthPoints(15)
                .withItem(Armor(protection = 5))
                .withItem(Shield(protection = 5))
                .run { this damagedBy 10 }

            // then
            hero.fightingPower.health shouldBe 14.hp
        }
    }

    @Nested
    inner class HeroIsHealed {
        @Test
        fun `hero is healed`() {
            // given
            // when
            val hero = aHero()
                .withHealthPoints(5)
                .run { this healedBy 5 }

            // then
            hero.fightingPower.might shouldBe Might(base = 15)
            hero.fightingPower.health shouldBe 10.hp
        }

        @Test
        fun `hero cannot be heal more than might level`() {
            // given
            // when
            val hero = aHero()
                .withHealthPoints(15)
                .run { this healedBy 5 }

            // then
            hero.fightingPower.might shouldBe Might(base = 15)
            hero.fightingPower.health shouldBe 15.hp
        }
    }

    @Nested
    inner class HeroIsStuffed {
        @Test
        fun `hero wears an armor`() {
            // given
            val powerArmor = Armor(enhancements = Enhancements(power = 15.power))

            // when
            val hero = aHero().withItem(powerArmor)

            // then
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 15)
            hero.fightingPower.health shouldBe 30.hp
        }

        @Test
        fun `hero wears two pieces of gear`() {
            // given
            val armor = Armor(enhancements = Enhancements(power = 5.power))
            val bracers = Bracers(enhancements = Enhancements(power = 5.power))

            // when
            val hero = aHero()
                .withItem(armor)
                .withItem(bracers)

            // then
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 10)
            hero.fightingPower.health shouldBe 25.hp
        }

        @Test
        fun `hero put on an armor`() {
            // given
            val armor = Armor(enhancements = Enhancements(power = 5.power))

            // when
            val hero = aHero()
                .run { this putOn armor }

            // then
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 5)
            hero.fightingPower.health shouldBe 20.hp
        }

        @Test
        fun `hero replace it's armor`() {
            // given
            val weakArmor = Armor(enhancements = Enhancements(power = 5.power))
            val strongArmor = Armor(enhancements = Enhancements(power = 25.power))

            // when
            val hero = aHero()
                .withItem(weakArmor)
                .run {
                    this putOn strongArmor
                }
                .run {
                    this putOn weakArmor
                }
                .run {
                    this putOn strongArmor
                }

            // then
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 25)
            hero.fightingPower.health shouldBe 40.hp
        }

        @Test
        fun `hero put on a third ring`() {
            // given
            val primaryRing = Ring(enhancements = Enhancements(power = 5.power))
            val secondaryRing = Ring(enhancements = Enhancements(power = 10.power))
            val replacementRing = Ring(enhancements = Enhancements(power = 15.power))

            // when
            val hero = aHero()
                .withItem(primaryRing)
                .withItem(secondaryRing)
                .run { this putOn replacementRing }

            // then
            hero.gear.arms.rings.primary shouldBe replacementRing
            hero.gear.arms.rings.secondary shouldBe secondaryRing
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 25)
        }

        @Test
        fun `hero replaces both weapon slots by a two handed weapon`() {
            // given
            val oneHandedWeapon = Weapon(type = ONE_HANDED)
            val shield = Shield()

            val twoHandedWeapon = Weapon(type = TWO_HANDED)

            // when
            val hero = aHero()
                .withItem(oneHandedWeapon)
                .withItem(shield)
                .run { this putOn twoHandedWeapon }

            // then
            hero.gear.weapons.mainHand shouldBe twoHandedWeapon
            hero.gear.weapons.offHand shouldBe Shield.emptySlot
        }

        @Test
        fun `hero put on off-hand removes two handed weapon from main hand`() {
            // given
            val twoHandedWeapon = Weapon(type = TWO_HANDED)
            val shield = Shield()

            // when
            val hero = aHero()
                .withItem(twoHandedWeapon)
                .run { this putOn shield }

            // then
            hero.gear.weapons.mainHand shouldBe Weapon.emptySlot
            hero.gear.weapons.offHand shouldBe shield
        }

        @Test
        fun `hero put off an armor`() {
            // given
            val armor = Armor(enhancements = Enhancements(power = 5.power))

            // when
            val hero = aHero()
                .withItem(armor)
                .run { this putOff gear.chest.armor }

            // then
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 0)
            hero.fightingPower.health shouldBe 15.hp
        }

        @Test
        fun `damaged hero put on armor get health increased`() {
            // given
            val armor = Armor(enhancements = Enhancements(power = 5.power))

            // when
            val hero = aHero()
                .damagedBy(10)
                .run { this putOn armor }

            // then
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 5)
            hero.fightingPower.health shouldBe 10.hp
        }

        @Test
        fun `hero don't die when removes a piece of gear`() {
            // given
            val armor = Armor(enhancements = Enhancements(power = 5.power))

            // when
            val hero = aHero()
                .withItem(armor)
                .damagedBy(15)
                .run { this putOff gear.chest.armor }

            // then
            hero.fightingPower.might shouldBe Might(base = 15, bonus = 0)
            hero.fightingPower.health shouldBe 1.hp
        }
    }

    @Nested
    inner class HeroInventory {
        @Test
        fun `hero store item`() {
            // given
            val weapon = Weapon(type = ONE_HANDED)

            // when
            val hero = aHero()
                .run { this store weapon }

            // then
            hero.inventory.items.contains(weapon) shouldBe true
        }

        @Test
        fun `hero take out item`() {
            // given
            val weapon = Weapon(type = ONE_HANDED)

            // when
            val hero = aHero()
                .withInventory(weapon)
                .run { this takeOut weapon }

            // then
            hero.inventory.items.contains(weapon) shouldBe false
        }
    }

    @Nested
    inner class Fails {
        @Test
        fun `should throw bad name exception`() {
            shouldThrow<IllegalArgumentException> {
                aRandomHeroInNormalMode("MyHer0")
            }
                .apply { message shouldBe "Name must contains only letters." }

        }

        @Test
        fun `should throw strength exception`() {
            shouldThrow<IllegalArgumentException> {
                Hero(
                    identity = aHeroIdentity(),
                    abilities = Abilities(
                        strength = 0.strength,
                        agility = 1.agility,
                        perception = 1.perception
                    ),
                    fightingPower = FightingPower(might = Might(base = 2))
                )
            }
                .apply { message shouldBe "An ability should be positive. Value = 0." }
        }

        @Test
        fun `should throw agility exception`() {
            shouldThrow<IllegalArgumentException> {
                Hero(
                    identity = aHeroIdentity(),
                    abilities = Abilities(
                        strength = 1.strength,
                        agility = 0.agility,
                        perception = 1.perception
                    ),
                    fightingPower = FightingPower(might = Might(base = 2))
                )
            }
                .apply { message shouldBe "An ability should be positive. Value = 0." }
        }

        @Test
        fun `should throw perception exception`() {
            shouldThrow<IllegalArgumentException> {
                Hero(
                    identity = aHeroIdentity(),
                    abilities = Abilities(
                        strength = 1.strength,
                        agility = 1.agility,
                        perception = 0.perception
                    ),
                    fightingPower = FightingPower(might = Might(base = 2))
                )
            }
                .apply { message shouldBe "An ability should be positive. Value = 0." }

        }
    }
}