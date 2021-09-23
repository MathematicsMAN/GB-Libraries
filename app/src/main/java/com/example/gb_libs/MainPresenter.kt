package com.example.gb_libs

import com.example.gb_libs.model.GitHubUser
import com.example.gb_libs.model.GitHubUsersRepo
import com.example.gb_libs.presentation.IUserListPresenter
import com.example.gb_libs.screens.AndroidScreens
import com.example.gb_libs.view.UserItemView
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