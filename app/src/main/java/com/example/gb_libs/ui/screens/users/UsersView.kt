package com.example.gb_libs.ui.screens.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UsersView : MvpView {
    fun  init()
    fun updateList()
}