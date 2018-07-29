package me.rikinmarfatia.skeletor

import android.app.Application
import me.rikinmarfatia.skeletor.dagger.AppComponent
import me.rikinmarfatia.skeletor.dagger.DaggerAppComponent

class SkeletorApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}