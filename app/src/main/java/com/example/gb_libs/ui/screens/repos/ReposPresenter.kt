package com.example.gb_libs.ui.screens.repos

import com.example.gb_libs.data.GitHubRepo
import com.example.gb_libs.data.GitHubReposRepo
import com.example.gb_libs.navigation.AndroidScreens
import com.example.gb_libs.ui.items.IRepoListPresenter
import com.example.gb_libs.ui.screens.repos.adapter.RepoItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber

class ReposPresenter(
    private val reposRepo: GitHubReposRepo,
    private val router: Router
) : MvpPresenter<ReposView>() {

    class RepoListPresenter : IRepoListPresenter {

        val repos = mutableListOf<GitHubRepo>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount(): Int = repos.size

        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            view.showRepo(repo.name.orEmpty())
        }
    }

    val reposListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        reposListPresenter.itemClickListener = { itemView ->
            val screen = AndroidScreens.RepoScreen(
                reposListPresenter.repos[itemView.pos]
            )
            router.navigateTo(screen)
        }
    }

    private fun loadData() {
        reposRepo.getRepos(ReposFragment.urlRepos)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ repos ->
                reposListPresenter.repos.addAll(repos)
                viewState.updateList()
            }, {
                Timber.e(it, "Ошибка получения списка репозиториев")
            })
    }

    fun backPressed(): Boolean {
        router.backTo(AndroidScreens.UsersScreen())
        return true
    }
}