package com.example.gb_libs.ui.items

import com.example.gb_libs.ui.screens.repos.adapter.RepoItemView

interface IListReposPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IRepoListPresenter : IListReposPresenter<RepoItemView>