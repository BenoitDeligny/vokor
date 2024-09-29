package games.vokor.adventuring.domain.monsters

import games.vokor.adventuring.domain.fight.FightingPower
import games.vokor.adventuring.domain.fight.HealthPoints
import games.vokor.adventuring.domain.storages.gear.Gear
import games.vokor.adventuring.domain.fight.Might

object MonsterFactory {

    fun aMonster() = Monster(
        identity = Identity(name = "MyMonster"),
        fightingPower = FightingPower(might = Might(base = 10)),
        gear = Gear()
    )

    fun Monster.withName(name: String) = copy(
        identity = identity.copy(name = name)
    )

    fun Monster.withPower(power: Int) = copy(
        fightingPower = fightingPower.copy(might = Might(base = power))
    )

    fun Monster.withHealthPoints(health: Int) = copy(
        fightingPower = fightingPower.copy(health = HealthPoints(health))
    )

    fun Monster.withGear(gear: Gear) = copy(
        gear = gear
    )

    fun aWeakMonster() = Monster(
        identity = Identity(name = "Weak Monster"),
        fightingPower = FightingPower(might = Might(base = 10)),
        gear = Gear()
    )

    fun aMediumMonster() = Monster(
        identity = Identity(name = "Medium Monster"),
        fightingPower = FightingPower(might = Might(base = 30)),
        gear = Gear()
    )

    fun aStrongMonster() = Monster(
        identity = Identity(name = "Strong Monster"),
        fightingPower = FightingPower(might = Might(base = 60)),
        gear = Gear()
    )
}
