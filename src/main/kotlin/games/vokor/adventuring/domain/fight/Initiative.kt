package games.vokor.adventuring.domain.fight

data class Initiative(
    val value: Int,
    val agility: Int,
) : Comparable<Initiative> {

    override fun compareTo(other: Initiative): Int =
        compareValuesBy(other, this, Initiative::value, Initiative::agility)
}
