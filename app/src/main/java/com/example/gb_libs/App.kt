package com.example.gb_libs

import android.app.Application
import com.example.gb_libs.di.component.AppComponent
import com.example.gb_libs.di.component.DaggerAppComponent
import com.example.gb_libs.di.component.RepoSubcomponent
import com.example.gb_libs.di.component.UsersSubcomponent
import com.example.gb_libs.di.modules.AppModule
import com.github.moxy_community.moxy.androidx.BuildConfig
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    var usersSubcomponent: UsersSubcomponent? = null
        private set

    var repoSubcomponent: RepoSubcomponent? = null
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

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        usersSubcomponent = it
    }

    fun initRepoSubcomponent() = appComponent
        .userSubcomponent()
        .repositorySubcomponent()
        .also {
            repoSubcomponent = it
        }

    fun releaseUserScope() {
        usersSubcomponent = null
    }

    fun releaseRepoScope() {
        repoSubcomponent = null
    }

    companion object {
        lateinit var instance: App
    }
}
