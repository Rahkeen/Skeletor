package me.rikinmarfatia.skeletor.datasource

import io.reactivex.Single
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon/")
    fun getPokemon() : Single<PokemonListResponse>
}
