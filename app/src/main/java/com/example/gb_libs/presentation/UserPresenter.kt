package com.example.gb_libs.presentation

import com.example.gb_libs.screens.AndroidScreens
import com.example.gb_libs.view.ui.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(val router: Router) : MvpPresenter<UserView>() {

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }
}