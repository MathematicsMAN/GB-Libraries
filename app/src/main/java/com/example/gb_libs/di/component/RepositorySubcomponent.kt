package com.example.gb_libs.di.component

import com.example.gb_libs.di.modules.RepoModule
import com.example.gb_libs.di.scopes.RepoScope
import com.example.gb_libs.ui.screens.repos.ReposPresenter
import dagger.Subcomponent

@RepoScope
@Subcomponent(
    modules = [RepoModule::class]
)
interface RepoSubcomponent {
    fun inject(reposPresenter: ReposPresenter)
}