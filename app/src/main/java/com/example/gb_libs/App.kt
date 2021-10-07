package com.example.gb_libs

import android.app.Application
import com.example.gb_libs.di.modules.AppComponent
import com.example.gb_libs.di.modules.AppModule
import com.example.gb_libs.di.modules.DaggerAppComponent
import com.github.moxy_community.moxy.androidx.BuildConfig
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    lateinit var testAppComponent: TestAppComponent
        private set

    var authorsSubcomponent: AuthorsSubcomponent? = null
        private set

    var booksSubcomponent: BooksSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initAuthorsSubcomponent() = testAppComponent.authorsSubcomponent().also {
        authorsSubcomponent = it
    }

    fun destroyAuthorsSubcomponent() {
        authorsSubcomponent = null
    }

    fun initBooksSubComponent() = testAppComponent
        .authorsSubcomponent()
        .booksSubcomponent()
        .also {
            booksSubcomponent = it
        }

    fun destroyBooksSubcomponent() {
        booksSubcomponent = null
    }


    companion object {
        lateinit var instance: App
    }
}
