package me.rikinmarfatia.skeletor.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import me.rikinmarfatia.skeletor.R
import me.rikinmarfatia.skeletor.SkeletorApplication
import me.rikinmarfatia.skeletor.presentation.PokemonViewModel
import me.rikinmarfatia.skeletor.presentation.PokemonViewState
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pokemonViewModel: PokemonViewModel
    lateinit var pokemonList: RecyclerView
    lateinit var pokemonLoader: ProgressBar
    lateinit var pokemonAdapter: PokemonAdapter

    private val disposables: CompositeDisposable = CompositeDisposable()

    companion object {
        val TAG = MainActivity::class.java.canonicalName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        (application as SkeletorApplication).appComponent.inject(this)

        pokemonList = findViewById(R.id.rv_pokemon)
        pokemonLoader = findViewById(R.id.loader_pokemon)
        pokemonAdapter = PokemonAdapter()
        pokemonList.adapter = pokemonAdapter
        pokemonList.layoutManager = LinearLayoutManager(baseContext)

        pokemonViewModel.getPokemon()
    }

    override fun onStart() {
        super.onStart()

        disposables.add(pokemonViewModel.viewState()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::render))
    }

    override fun onStop() {
        super.onStop()

        disposables.clear()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

    private fun render(viewState: PokemonViewState) {
        when (viewState) {
            is PokemonViewState.Loading -> pokemonLoader.visibility = View.VISIBLE
            is PokemonViewState.Success -> {
                pokemonLoader.visibility = View.GONE
                pokemonAdapter.setData(viewState.pokemonList)
            }
            is PokemonViewState.Error -> {
                pokemonLoader.visibility = View.GONE
                Log.e(TAG, viewState.error.message)
            }
        }
    }
}
