package games.vokor.gaming.domain.storages

import games.vokor.gaming.domain.items.Item

interface Storage {
    fun add(item: Item): Storage
    fun removes(item: Item): Storage
}