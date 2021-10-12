package com.example.gb_libs.presentation

import android.util.Log
import com.example.gb_libs.model.GitHubUser
import com.example.gb_libs.model.GitHubUsersRepo
import com.example.gb_libs.screens.AndroidScreens
import com.example.gb_libs.view.UserItemView
import com.example.gb_libs.view.ui.UsersView
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
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
            val screen = AndroidScreens.UserScreen(usersListPresenter.users[itemView.pos])
            router.navigateTo(screen)
        }
    }

    private val TAG = "UsersObserve"
    private val users: MutableList<GitHubUser> = mutableListOf()

    fun loadData() {
        val userObserver = object : Observer<GitHubUser> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe")
            }

            override fun onNext(user: GitHubUser) {
                Log.d(TAG, "onNext: ${user.login}")
                users.add(user)
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }

        }

        usersRepo.getUsers()
            .subscribe(userObserver)

        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}