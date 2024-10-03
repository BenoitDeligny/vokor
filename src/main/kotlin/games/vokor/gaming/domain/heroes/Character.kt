package games.vokor.gaming.domain.heroes

import games.vokor.gaming.domain.items.Item

interface Character {
    infix fun putOn(item: Item): Character
    infix fun putOff(item: Item): Character
    infix fun store(item: Item): Character
    infix fun takeOut(item: Item): Character
}
