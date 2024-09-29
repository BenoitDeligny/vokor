package games.vokor.adventuring.application.adventures

import games.vokor.adventuring.domain.adventures.Difficulty
import games.vokor.adventuring.domain.heroes.HeroesFactory.aHero
import games.vokor.adventuring.infrastructure.adventures.InMemoryAllAdventures
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CreateAdventuresTest {
    private val allAdventures = InMemoryAllAdventures()
    private val createAdventures = CreateAdventures(allAdventures)

    @BeforeEach
    fun setUp() {
        allAdventures.clear()
    }

    @ParameterizedTest
    @EnumSource(Difficulty::class)
    fun `adventure is created with`(difficulty: Difficulty) {
        // given
        val hero = aHero()

        // when
        createAdventures.create(difficulty = difficulty, hero = hero)

        // then
        allAdventures.all().size shouldBe 1
        allAdventures.all().first().difficulty shouldBe difficulty
        allAdventures.all().first().hero shouldBe hero
    }
}