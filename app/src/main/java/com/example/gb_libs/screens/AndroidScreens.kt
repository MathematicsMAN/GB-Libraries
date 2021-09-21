package com.example.gb_libs.screens

import com.example.gb_libs.view.ui.UserFragment
import com.example.gb_libs.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {
    class UsersScreen(private val s: String): SupportAppScreen() {
        override fun getFragment() = UsersFragment.newInstance(s)
    }

    class UserScreen: SupportAppScreen() {
        override fun getFragment() = UserFragment()
    }
}