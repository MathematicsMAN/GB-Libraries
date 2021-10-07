package com.example.gb_libs.ui.activity

import com.example.gb_libs.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor(private var router: Router): MvpPresenter<MainView>() {

//    @Inject
//    lateinit

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.UsersScreen())
    }

    fun backPressed() {
        router.exit()
    }
}