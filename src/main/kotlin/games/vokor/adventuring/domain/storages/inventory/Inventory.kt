package games.vokor.adventuring.domain.storages.inventory

import games.vokor.adventuring.domain.storages.Storage
import games.vokor.adventuring.domain.items.Item

data class Inventory(
    val items: List<Item> = emptyList(),
) : Storage {
    companion object {
        val EMPTY = Inventory()
    }

    override fun add(item: Item): Inventory = copy(items = items + item)

    override fun removes(item: Item): Inventory = copy(items = items - item)
}
