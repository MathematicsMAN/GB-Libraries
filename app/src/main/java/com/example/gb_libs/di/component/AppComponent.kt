package com.example.gb_libs.di.component

import com.example.gb_libs.di.modules.ApiModule
import com.example.gb_libs.di.modules.AppModule
import com.example.gb_libs.di.modules.DbModule
import com.example.gb_libs.di.modules.CiceroneModule
import com.example.gb_libs.ui.activity.MainActivity
import com.example.gb_libs.ui.activity.MainPresenter
import com.example.gb_libs.ui.screens.repo.RepoPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DbModule::class,
        CiceroneModule::class,
    ]
)
interface AppComponent {

    fun userSubcomponent(): UsersSubcomponent

    fun presenter(): MainPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

//    fun inject(usersPresenter: UsersPresenter)
    fun inject(repoPresenter: RepoPresenter)
//    fun inject(reposPresenter: ReposPresenter)
}