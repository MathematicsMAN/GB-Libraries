package com.example.gb_libs

import android.app.Application
import com.example.gb_libs.data.db.GitHubDatabase
import com.example.gb_libs_lesson1.BuildConfig
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber

class App : Application() {
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigationHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        GitHubDatabase.create(this)
    }

    companion object {
        lateinit var instance: App
    }
}