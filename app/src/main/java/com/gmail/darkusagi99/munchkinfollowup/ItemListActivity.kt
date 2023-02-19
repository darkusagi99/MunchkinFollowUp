package com.gmail.darkusagi99.munchkinfollowup

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.gmail.darkusagi99.munchkinfollowup.player.PlayerInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random


/**
 * Only activity for the APP
 */
class ItemListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->

            val taskEditText =  EditText(view.context)
            val dialogClickListener =
                    DialogInterface.OnClickListener { _, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                Toast.makeText(this, "Création - " + taskEditText.text.toString(), Toast.LENGTH_SHORT).show()
                                PlayerInfo.addItem(PlayerInfo.createNewPlayer(taskEditText.text.toString()))
                            }
                            DialogInterface.BUTTON_NEGATIVE -> {
                                Toast.makeText(this, "Pas de Création - " + taskEditText.text.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
            val ab: AlertDialog.Builder = AlertDialog.Builder(view.context)
            ab.setMessage("Ajouter nouveau joueur ?")
                    .setView(taskEditText)
                    .setPositiveButton("Oui", dialogClickListener)
                    .setNegativeButton("Non", dialogClickListener).show()
        }

        findViewById<FloatingActionButton>(R.id.dice).setOnClickListener { view ->

            val currentRoll = Random.nextInt(1, 7)
            val ab: AlertDialog.Builder = AlertDialog.Builder(view.context)
            ab.setMessage("Lancé de dés : $currentRoll")
                .show()
        }

        setupRecyclerView(findViewById(R.id.item_list))
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(PlayerInfo.ITEMS)
    }

    class SimpleItemRecyclerViewAdapter(private val values: List<PlayerInfo.PlayerItem>) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = item.id
            holder.levelView.text = item.level.toString()

            holder.addLevelView.setOnClickListener {
                PlayerInfo.addLevelToPlayer(item.id)
                this.notifyItemChanged(position)
            }

            holder.removeLevelView.setOnClickListener {
                PlayerInfo.removeLevelToPlayer(item.id)
                this.notifyItemChanged(position)
            }

            with(holder.itemView) {
                tag = item
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.findViewById(R.id.id_text)
            val levelView: TextView = view.findViewById(R.id.level_text)
            val addLevelView: ImageButton = view.findViewById(R.id.levelup)
            val removeLevelView: ImageButton = view.findViewById(R.id.leveldown)
        }
    }
}