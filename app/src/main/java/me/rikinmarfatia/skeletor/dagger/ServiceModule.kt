package me.rikinmarfatia.skeletor.dagger

import dagger.Module
import dagger.Provides
import me.rikinmarfatia.skeletor.datasource.PokemonService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://pokeapi.co/api/v2/")
                .build()
    }

    @Provides
    @Singleton
    fun providesPokemonService(retrofit: Retrofit): PokemonService {
        return retrofit.create(PokemonService::class.java)
    }
}