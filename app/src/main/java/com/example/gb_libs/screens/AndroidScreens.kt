package com.example.gb_libs.screens

import com.example.gb_libs.model.GitHubUser
import com.example.gb_libs.view.ui.UserFragment
import com.example.gb_libs.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class UserScreen(private val user: GitHubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }
}