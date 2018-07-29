package me.rikinmarfatia.skeletor.presentation

import me.rikinmarfatia.skeletor.datasource.PokemonResponse

sealed class PokemonViewState {
    data class Success(val pokemonList: List<PokemonResponse>): PokemonViewState()
    class Loading: PokemonViewState()
    data class Error(val error: Throwable): PokemonViewState()
}