package com.example.repasoexamenlourdes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.repasoexamenlourdes.R
import com.example.repasoexamenlourdes.data.Juego
import com.example.repasoexamenlourdes.databinding.ItemGameBinding

class GameAdapter(private val listener: OnGameClick): ListAdapter<Juego, GameAdapter.GameViewHolder>(GAME_COMPARATOR) {

    private var selectedPosition = RecyclerView.NO_POSITION

    interface OnGameClick{
        fun onGameLongClick(view: View, juego: Juego)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(layoutInflater.inflate(R.layout.item_game, parent, false))
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = getItem(position)
        holder.render(item)
    }

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemGameBinding.bind(itemView)

        fun render(juego: Juego){
            with(binding){
                gameId.text = juego.id.toString()
                gameName.text = juego.nombre
                //gameCategory.text = juego.category.toString()

                root.setOnLongClickListener {
                    selectedPosition = adapterPosition
                    listener.onGameLongClick(itemView, juego)
                    true
                }
            }
        }

    }

    companion object{
        private val GAME_COMPARATOR = object :DiffUtil.ItemCallback<Juego>(){
            override fun areItemsTheSame(oldItem: Juego, newItem: Juego): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Juego, newItem: Juego): Boolean {
                return oldItem.nombre == newItem.nombre
            }

        }
    }
}