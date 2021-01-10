package com.gmail.darkusagi99.munchkinfollowup.player

import android.widget.Toast
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

    fun addItem(item: PlayerItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): PlayerItem {
        return PlayerItem(position.toString(), 1)
    }

    fun createNewPlayer(name: String): PlayerItem {
        return PlayerItem(name, 1)
    }

    fun addLevelToPlayer(id : String) {
        updateLevel(id, 1)
    }

    fun removeLevelToPlayer(id : String) {
        updateLevel(id, -1)
    }

    private fun updateLevel(id : String, value : Int) {
        val currentPlayer = ITEM_MAP[id]
        val newLevel = (currentPlayer?.level ?: 1) + value
        if (newLevel in 1..10) {
            if (currentPlayer != null) {
                currentPlayer.level = newLevel
            }
        }
    }

    /**
     * An item representing a player
     */
    data class PlayerItem(val id: String, var level: Int) {
        override fun toString(): String = "$id - Niveau : $level"
    }
}