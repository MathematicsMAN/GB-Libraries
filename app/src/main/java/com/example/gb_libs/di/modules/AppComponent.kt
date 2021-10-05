package com.example.gb_libs.di.modules

import com.example.gb_libs.ui.activity.MainActivity
import com.example.gb_libs.ui.activity.MainPresenter
import com.example.gb_libs.ui.screens.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)
}