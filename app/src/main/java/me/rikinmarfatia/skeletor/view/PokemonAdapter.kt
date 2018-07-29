package me.rikinmarfatia.skeletor.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.rikinmarfatia.skeletor.R
import me.rikinmarfatia.skeletor.datasource.PokemonResponse
import java.util.*

class PokemonAdapter: RecyclerView.Adapter<PokemonViewHolder> {

    private var pokemonData: List<PokemonResponse>

    constructor(pokemonData: List<PokemonResponse> = Collections.emptyList()): super() {
        this.pokemonData = pokemonData
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): PokemonViewHolder {
        val view: View = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recylerview_pokemon_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonData.size
    }

    override fun onBindViewHolder(viewHolder: PokemonViewHolder, position: Int) {
        viewHolder.bind(pokemonData[position].name)
    }

    fun setData(pokemonData: List<PokemonResponse>) {
        this.pokemonData = pokemonData
        notifyDataSetChanged()
    }

}