package me.rikinmarfatia.skeletor.presentation

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import me.rikinmarfatia.skeletor.datasource.PokemonListResponse
import me.rikinmarfatia.skeletor.datasource.PokemonRepository
import javax.inject.Inject

class PokemonViewModel @Inject constructor(private val pokemonRepository: PokemonRepository) {

    private val states: BehaviorSubject<PokemonViewState> = BehaviorSubject.create()
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun viewState(): Observable<PokemonViewState> {
        return states
    }

    fun getPokemon() {
        disposables.add(pokemonRepository
                .getPokemon()
                .doOnSubscribe({ _ -> states.onNext(PokemonViewState.Loading()) })
                .subscribe({ success: PokemonListResponse ->
                    states.onNext(PokemonViewState.Success(success.results))
                }, { error: Throwable ->
                    states.onNext(PokemonViewState.Error(error))
                }))
    }
}