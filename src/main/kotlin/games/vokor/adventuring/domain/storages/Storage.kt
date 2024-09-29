package games.vokor.adventuring.domain.storages

import games.vokor.adventuring.domain.items.Item

interface Storage {
    fun add(item: Item): Storage
    fun removes(item: Item): Storage
}