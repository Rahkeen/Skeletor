package me.rikinmarfatia.skeletor.dagger

import dagger.Component
import me.rikinmarfatia.skeletor.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ServiceModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}