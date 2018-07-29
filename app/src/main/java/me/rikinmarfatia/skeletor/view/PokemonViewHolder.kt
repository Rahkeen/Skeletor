package me.rikinmarfatia.skeletor.view

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import me.rikinmarfatia.skeletor.R

class PokemonViewHolder: RecyclerView.ViewHolder {
  val pokemonName: TextView

  constructor(itemView: View) : super(itemView) {
    pokemonName = itemView.findViewById(R.id.tv_pokemon_name)
  }

  fun bind(name: String) {
    pokemonName.text = name
  }
}