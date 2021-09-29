package com.example.gb_libs.navigation

import com.example.gb_libs.data.GitHubRepo
import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.ui.screens.repo.RepoFragment
import com.example.gb_libs.ui.screens.repos.ReposFragment
import com.example.gb_libs.ui.screens.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {
    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class ReposScreen(private val user: GitHubUser) : SupportAppScreen() {
        override fun getFragment() = ReposFragment.newInstance(user)
    }

    class RepoScreen(private val repo: GitHubRepo) : SupportAppScreen() {
        override fun getFragment() = RepoFragment.newInstance(repo)
    }
}