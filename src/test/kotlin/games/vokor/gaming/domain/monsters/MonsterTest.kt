package games.vokor.gaming.domain.monsters

import games.vokor.gaming.domain.fighters.HealthPoints.Companion.hp
import games.vokor.gaming.domain.storages.gear.Chest
import games.vokor.gaming.domain.storages.gear.Gear
import games.vokor.gaming.domain.storages.gear.Weapons
import games.vokor.gaming.domain.items.Item.Armor
import games.vokor.gaming.domain.items.Item.Shield
import games.vokor.gaming.domain.monsters.MonsterFactory.aMonster
import games.vokor.gaming.domain.monsters.MonsterFactory.withGear
import games.vokor.gaming.domain.monsters.MonsterFactory.withHealthPoints
import games.vokor.gaming.domain.monsters.MonsterFactory.withPower
import io.kotest.matchers.ranges.shouldBeIn
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

class MonsterTest {
    @RepeatedTest(100)
    fun `basic monster`() {
        // given
        // when
        // then
        aMonster().initiative().value shouldBeIn 4..9
        aMonster().initiative().agility shouldBe 3
        aMonster().attacks() shouldBeIn 1..6
    }

    @Test
    fun `monster take damages`() {
        // given
        // when
        val monster = aMonster()
            .run { this damagedBy (5) }

        // then
        monster.fightingPower.might.total shouldBe 10
        monster.fightingPower.health shouldBe 5.hp
        monster.dead() shouldBe false
    }

    @Test
    fun `monster take damages and dies`() {
        // given
        // when
        val monster = aMonster()
            .run { this damagedBy (15) }

        // then
        monster.fightingPower.might.total shouldBe 10
        monster.fightingPower.health shouldBe 0.hp
        monster.dead() shouldBe true
    }

    @Test
    fun `monster cannot take more damages than healthPoints`() {
        // given
        // when
        val monster = aMonster()
            .run { this damagedBy (15) }

        // then
        monster.fightingPower.might.total shouldBe 10
        monster.fightingPower.health shouldBe 0.hp
        monster.dead() shouldBe true
    }

    @Test
    fun `monster take less damages due to protection of armor`() {
        // given
        // when
        val monster = aMonster()
            .withPower(15)
            .withHealthPoints(15)
            .withGear(Gear(chest = Chest(Armor(protection = 5))))
            .run { this damagedBy 10 }

        // then
        monster.fightingPower.health shouldBe 10.hp
    }

    @Test
    fun `monster take less damages due to protection of armor and shield`() {
        // given
        // when
        val monster = aMonster()
            .withPower(15)
            .withHealthPoints(15)
            .withGear(
                Gear(
                    chest = Chest(Armor(protection = 5)),
                    weapons = Weapons(offHand = Shield(protection = 5))
                )
            )
            .run { this damagedBy 15 }

        // then
        monster.fightingPower.health shouldBe 10.hp
    }

    @Test
    fun `monster lose one health point even the protection is over damages`() {
        // given
        // when
        val monster = aMonster()
            .withPower(15)
            .withHealthPoints(15)
            .withGear(
                Gear(
                    chest = Chest(Armor(protection = 5)),
                    weapons = Weapons(offHand = Shield(protection = 5))
                )
            )
            .run { this damagedBy 10 }

        // then
        monster.fightingPower.health shouldBe 14.hp
    }

    @Test
    fun `monster is healed`() {
        // given
        // when
        val monster = aMonster()
            .withHealthPoints(5)
            .run { this healedBy (5) }

        // given
        monster.fightingPower.health shouldBe 10.hp
    }

    @Test
    fun `monster is healed and cannot exceed max health`() {
        // given
        // when
        val monster = aMonster()
            .withHealthPoints(5)
            .run { this healedBy (10) }

        // given
        monster.fightingPower.health shouldBe 10.hp
    }
}