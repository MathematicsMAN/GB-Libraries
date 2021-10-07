package com.example.gb_libs.di.component

import com.example.gb_libs.di.modules.UserModule
import com.example.gb_libs.di.scopes.UserScope
import com.example.gb_libs.ui.screens.users.UsersPresenter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [UserModule::class]
)
interface UsersSubcomponent {
    fun repositorySubcomponent(): RepoSubcomponent

    fun inject(usersPresenter: UsersPresenter)
}