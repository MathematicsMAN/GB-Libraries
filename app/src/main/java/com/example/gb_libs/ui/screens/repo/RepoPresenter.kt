package com.example.gb_libs.ui.screens.repo

import com.example.gb_libs.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepoPresenter : MvpPresenter<RepoView>() {

    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }
}