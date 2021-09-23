package com.example.gb_libs

import com.example.gb_libs.screens.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}