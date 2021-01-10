package com.gmail.darkusagi99.munchkinfollowup.player

import android.widget.Toast
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing player content for interface
 */
object PlayerInfo {

    /**
     * An array of players items.
     */
    val ITEMS: MutableList<PlayerItem> = ArrayList()

    /**
     * A map of players items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlayerItem> = HashMap()

    fun addItem(item: PlayerItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
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