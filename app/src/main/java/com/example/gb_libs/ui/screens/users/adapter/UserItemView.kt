package com.example.gb_libs.ui.screens.users.adapter

import com.example.gb_libs.ui.items.IItemView

interface UserItemView : IItemView {
    fun showLogin(login: String)
    fun loadAvatar(url: String)
}