package com.example.gb_libs.ui.items

import com.example.gb_libs.ui.screens.users.adapter.UserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter: IListPresenter<UserItemView>