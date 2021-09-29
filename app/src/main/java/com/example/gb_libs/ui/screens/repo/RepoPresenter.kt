package com.example.gb_libs.ui.screens.repo

import com.example.gb_libs.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(private val router: Router) : MvpPresenter<RepoView>() {

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }
}