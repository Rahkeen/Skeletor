package me.rikinmarfatia.skeletor.datasource

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepository @Inject constructor(private val pokemonService: PokemonService) {

    fun getPokemon(): Single<PokemonListResponse> {
        return pokemonService
                .getPokemon()
                .subscribeOn(Schedulers.io())
    }
}
