package com.gmail.darkusagi99.munchkinfollowup.player

import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlayerInfo {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<PlayerItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlayerItem> = HashMap()

    private val COUNT = 25

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createDummyItem(i))
        }
    }

    private fun addItem(item: PlayerItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): PlayerItem {
        return PlayerItem(position.toString(), "Nom " + position, makeDetails(position))
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Niveau : ").append(position)
        return builder.toString()
    }

    /**
     * An item representing a player
     */
    data class PlayerItem(val id: String, val name: String, val level: String) {
        override fun toString(): String = level
    }
}