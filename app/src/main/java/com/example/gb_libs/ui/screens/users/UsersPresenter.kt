package com.example.gb_libs.ui.screens.users

import com.example.gb_libs.App
import com.example.gb_libs.data.GitHubUser
import com.example.gb_libs.data.GitHubUsersRepo
import com.example.gb_libs.navigation.AndroidScreens
import com.example.gb_libs.ui.items.IUserListPresenter
import com.example.gb_libs.ui.screens.users.adapter.UserItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {
    @Inject
    lateinit var usersRepo: GitHubUsersRepo

    @Inject
    lateinit var router: Router

    class UserListPresenter : IUserListPresenter {

        val users = mutableListOf<GitHubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login.orEmpty())
            view.loadAvatar(user.avatarUrl.orEmpty())
        }
    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val screen = AndroidScreens.ReposScreen(
                usersListPresenter.users[itemView.pos]
            )
            router.navigateTo(screen)
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                Timber.e(it, "Ошибка получения пользователей")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.releaseUserScope()
    }
}