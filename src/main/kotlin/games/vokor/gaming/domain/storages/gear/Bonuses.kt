package games.vokor.gaming.domain.storages.gear

interface Bonuses {
    fun power(): Int
    fun damages(): Int
    fun damageReduction(): Int
}