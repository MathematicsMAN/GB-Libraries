package com.example.gb_libs.presentation

import com.example.gb_libs.view.IItemView
import com.example.gb_libs.view.UserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter: IListPresenter<UserItemView>