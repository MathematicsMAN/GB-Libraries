package com.example.gb_libs.presentation

import com.example.gb_libs.model.GitHubUser
import com.example.gb_libs.model.GitHubUsersRepo
import com.example.gb_libs.screens.AndroidScreens
import com.example.gb_libs.view.UserItemView
import com.example.gb_libs.view.ui.UserFragment
import com.example.gb_libs.view.ui.UsersView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    val usersRepo: GitHubUsersRepo,
    val router: Router
) : MvpPresenter<UsersView>() {
    class UserListPresenter : IUserListPresenter {

        val users = mutableListOf<GitHubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login)
        }
    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val userGithub = usersListPresenter.users[itemView.pos]
            val screen = AndroidScreens.UserScreen(userGithub.login).apply {
                fragment.arguments?.putParcelable(UserFragment.KEY_USER_GITHUB, userGithub)
            }
            router.navigateTo(screen)
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}