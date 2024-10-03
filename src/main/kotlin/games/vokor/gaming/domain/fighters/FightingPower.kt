package games.vokor.gaming.domain.fighters

import games.vokor.gaming.domain.storages.gear.Gear
import games.vokor.gaming.domain.fighters.HealthPoints.Companion.hp

data class FightingPower(
    val might: Might,
    val health: HealthPoints = HealthPoints(might.total),
) {
    init {
        require(health <= might) { "Health cannot be greater than Threshold. Threshold = $might, Health = $health." }
    }

    fun increaseBy(gear: Gear): FightingPower {
        val bonus = gear.power()
        val newPower = might.copy(bonus = bonus)

        return copy(
            might = newPower,
            health = when {
                (health + bonus) <= newPower.total -> (health + bonus).hp
                else -> newPower.total.hp
            }
        )
    }

    fun decreaseBy(gear: Gear): FightingPower {
        val newPower = might.copy(bonus = gear.power())

        return copy(
            might = newPower,
            health = when {
                (health - might.bonus) >= 1 -> (health - might.bonus).hp
                else -> 1.hp
            }
        )
    }

    fun increaseBy(healing: Int): FightingPower = copy(
        health = when {
            (health + healing) <= might.total -> (health + healing).hp
            else -> (might.base).hp
        }
    )

    fun decreaseBy(damages: Int): FightingPower = copy(
        health = when {
            damages == 0 -> (health - 1).hp
            (health - damages) >= 0 -> (health - damages).hp
            else -> 0.hp
        }
    )
}
