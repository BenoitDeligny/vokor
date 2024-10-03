package games.vokor.gaming.domain.dice

import kotlin.random.Random

// TODO: do it better and not static
object Dice {
    fun d6Roll(): Int = roll(from = 1, until = 6)
    private fun roll(from: Int, until: Int): Int = Random.nextInt(from = from, until = until + 1)
}

