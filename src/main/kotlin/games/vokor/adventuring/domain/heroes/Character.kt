package games.vokor.adventuring.domain.heroes

import games.vokor.adventuring.domain.items.Item

interface Character {
    infix fun putOn(item: Item): Character
    infix fun putOff(item: Item): Character
    infix fun store(item: Item): Character
    infix fun takeOut(item: Item): Character
}
